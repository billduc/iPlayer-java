/*
 * OggVorbisInfoApplet.
 * 
 * JavaZOOM : jlgui@javazoom.net
 *            http://www.javazoom.net
 *
 *-----------------------------------------------------------------------
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU Library General Public License as published
 *   by the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this program; if not, write to the Free Software
 *   Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *----------------------------------------------------------------------
 */

package javazoom.jlgui.player.amp.tag;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jlgui.basicplayer.AppletVorbisSPIWorkaround;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class implements Applet workaround to make Ogg Vorbis TagInfo 
 * works under JavaPlugin.
 */
public class OggVorbisInfoApplet extends OggVorbisInfo
{
	private static Log log = LogFactory.getLog(OggVorbisInfoApplet.class);
	
	public OggVorbisInfoApplet()
	{
		super();
	}
	/**
	 * Load info from input stream.
	 * @param input
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	protected void loadInfo(InputStream input) throws IOException, UnsupportedAudioFileException
	{
	  AudioFileFormat aff = AppletVorbisSPIWorkaround.getAudioFileFormat(input);
	  super.loadInfo(aff);
	}

	/**
	 * Load Ogg Vorbis info from file.
	 * @param file
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	protected void loadInfo(File file) throws IOException, UnsupportedAudioFileException
	{
	  AudioFileFormat aff = AppletVorbisSPIWorkaround.getAudioFileFormat(file);
	  super.loadInfo(aff);
	}
  
	/**
	 * Load Ogg Vorbis info from URL.
	 * @param input
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	protected void loadInfo(URL input) throws IOException, UnsupportedAudioFileException
	{
		log.debug("loadInfo for Ogg Vorbis : "+input.toString());
		AudioFileFormat aff = AppletVorbisSPIWorkaround.getAudioFileFormat(input);
		super.loadInfo(aff);
		super.loadExtendedInfo(aff);			
	}
	
}
