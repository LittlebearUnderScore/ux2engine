/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uxsoft.engine.ux2.sound.ext;

import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import com.uxsoft.engine.ux2.sound.IDGSoundException;
import com.uxsoft.engine.ux2.sound.SoundCodec;
import com.uxsoft.engine.ux2.sound.SoundCodecProvider;
import com.uxsoft.engine.ux2.sound.SoundType;

/**
 *
 * @author David
 */
public class OGGSoundCodecProvider
		implements SoundCodecProvider {

	public SoundCodec createCodecFor(AbstractSound sound) {
		OGGCodec ret = new OGGCodec();
		ret.initialize(sound);
		return ret;
	}

	public Object getSoundType() {
		return SoundType.TYPE_OGG;
	}
	
	class OGGCodec
			implements SoundCodec {
		private int channels;
		private Page og;
		private Packet op;
		private StreamState os;
		private SyncState oy;
		private int rate;
		private Block vb;
		private Comment vc;
		private DspState vd;
		private Info vi;
		private final int BUFSIZE = 4096 * 2;
		private byte[] buffer = null;
		private int bytes = 0;
		private int convsize = BUFSIZE * 2;
		private boolean chained = false;
		private float[][][] _pcmf;
		private int[] _index;
		private byte[] convbuffer = new byte[convsize];
		private int index;

		private void initialize(AbstractSound sound) {
			oy = new SyncState();
			os = new StreamState();
			og = new Page();
			op = new Packet();
			vi = new Info();
			vc = new Comment();
			vd = new DspState();
			vb = new Block(vd);
			buffer = null;
			bytes = 0;
			oy.init();

			InputStream bitStream = sound.getInput();
			index = oy.buffer(BUFSIZE);

			buffer = oy.data;

			try {
				bytes = bitStream.read(buffer, index, BUFSIZE);
			} catch (Exception e) {
				throw new IDGSoundException(e);
			}

			oy.wrote(bytes);

			if (chained) {
				chained = false;
			} else {
				if (oy.pageout(og) != 1) {
					if (bytes < BUFSIZE) {
						return;
					}

					throw new IDGSoundException("Input does not appear to be an Ogg bitstream.");
				}
			}

			os.init(og.serialno());
			os.reset();
			vi.init();
			vc.init();

			if (os.pagein(og) < 0) {

				// error; stream version mismatch perhaps
				throw new IDGSoundException("Error reading first page of Ogg bitstream data.");
			}

			if (os.packetout(op) != 1) {

				// no page? must not be vorbis
				throw new IDGSoundException("Error reading initial header packet.");
			}

			if (vi.synthesis_headerin(vc, op) < 0) {

				// error case; not a vorbis header
				throw new IDGSoundException("This Ogg bitstream does not contain Vorbis audio data.");
			}

			int i = 0;

			while (i < 2) {
				while (i < 2) {
					int result = oy.pageout(og);

					if (result == 0) {
						break;    // Need more data
					}

					if (result == 1) {
						os.pagein(og);

						while (i < 2) {
							result = os.packetout(op);

							if (result == 0) {
								break;
							}

							if (result == -1) {
								throw new IDGSoundException("Corrupt secondary header.");
							}

							vi.synthesis_headerin(vc, op);
							i++;
						}
					}
				}

				index = oy.buffer(BUFSIZE);
				buffer = oy.data;

				try {
					bytes = bitStream.read(buffer, index, BUFSIZE);
				} catch (Exception e) {
					throw new IDGSoundException(e);
				}

				if ((bytes == 0) && (i < 2)) {
					throw new IDGSoundException("End of file before finding all Vorbis headers!");
				}

				oy.wrote(bytes);
			}

			convsize = BUFSIZE / vi.channels;
			vd.synthesis_init(vi);
			vb.init(vd);

			_pcmf = new float[1][][];
			_index = new int[vi.channels];
            AudioFormat audioFormat = new AudioFormat(vi.rate, 16, vi.channels, true,    // PCM_Signed
                false    // littleEndian
                    );
			sound.initializeLine(audioFormat);
		}

		public int decodeBytes(InputStream bitStream, byte[] writeTo, int maxLength) {
			int i = 0;
			int ret = 0;
			int eos = 0;
			int result = oy.pageout(og);

			if (result == 0) {
				ret = 0;    // need more data
			}

			if (result == -1) {    // missing or corrupt data at this page

				// position
				// System.err.println("Corrupt or missing data in
				// bitstream;
				// continuing...");
			} else {
				os.pagein(og);

				if (og.granulepos() == 0) {    //
					chained = true;    //
					eos = 1;    //
					ret = 0;    //
				}    //

				while (true) {
					result = os.packetout(op);

					if (result == 0) {
						break;    // need more data
					}

					if (result == -1) {    // missing or corrupt data at

						// this page position
						// no reason to complain; already complained
						// above
						// System.err.println("no reason to complain;
						// already complained above");
					} else {

						// we have a packet. Decode it
						int samples;

						if (vb.synthesis(op) == 0) {    // test for

							// success!
							vd.synthesis_blockin(vb);
						}

						while ((samples = vd.synthesis_pcmout(_pcmf, _index)) > 0) {
							float[][] pcmf = _pcmf[0];
							int bout = ((samples < convsize)
										? samples
										: convsize);

							// convert doubles to 16 bit signed ints
							// (host order) and
							// interleave
							for (i = 0; i < vi.channels; i++) {
								int ptr = i * 2;

								// int ptr=i;
								int mono = _index[i];

								for (int j = 0; j < bout; j++) {
									int val = (int) (pcmf[i][mono + j] * 32767.);

									if (val > 32767) {
										val = 32767;
									}

									if (val < -32768) {
										val = -32768;
									}

									if (val < 0) {
										val |= 0x8000;
									}

									convbuffer[ptr] = (byte) (val);
									convbuffer[ptr + 1] = (byte) (val >>> 8);
									ptr += 2 * (vi.channels);
								}
							}

							//outputLine.write(convbuffer, 0, 2 * vi.channels * bout);
							ret = 2 * vi.channels * bout;
							System.arraycopy(convbuffer, 0, writeTo, 0, ret);
							vd.synthesis_read(bout);
							try {
								Thread.sleep(1);
							} catch (Throwable t) {}
						}
					}
				}

				if (og.eos() != 0) {
					eos = 1;
				}
			}

			if (eos == 0) {
				index = oy.buffer(BUFSIZE);
				buffer = oy.data;

				try {
					bytes = bitStream.read(buffer, index, Math.min(BUFSIZE, maxLength));
				} catch (Exception e) {
					throw new IDGSoundException(e);
				}

				if (bytes == -1) {
					ret = 0;
				}

				oy.wrote(bytes);

				if (bytes == 0) {
					eos = 1;
				}
			}
			
			return ret;
		}
	}
}
