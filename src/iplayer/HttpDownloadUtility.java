/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iplayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author billd
 */
public class HttpDownloadUtility {
    private static final int BUFFER_SIZE = 4096;
    
    public static void downloadFile(String fileURL, String saveDir)
			throws IOException {
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = "";
			String disposition = httpConn.getHeaderField("Content-Disposition");
			String contentType = httpConn.getContentType();
			int contentLength = httpConn.getContentLength();

			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10,
							disposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
						fileURL.length());
			}
                        fileName = fileName + ".mp3";
			System.out.println("Content-Type = " + contentType);
			System.out.println("Content-Disposition = " + disposition);
			System.out.println("Content-Length = " + contentLength);
			System.out.println("fileName = " + fileName);

			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = saveDir + File.separator + fileName;
			
			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);
                        
                        int bytesRead = -1;
                                    byte[] buffer = new byte[BUFFER_SIZE];
                                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                                        outputStream.write(buffer, 0, bytesRead);
                                    }
//                        new Thread(){
//                            @Override
//                            public void run(){
//                                try {
//                                    int bytesRead = -1;
//                                    byte[] buffer = new byte[BUFFER_SIZE];
//                                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                                        outputStream.write(buffer, 0, bytesRead);
//                                    }       } catch (IOException ex) {
//                                    Logger.getLogger(HttpDownloadUtility.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                            
//                        };
			

			outputStream.close();
			inputStream.close();

			//System.out.println("File downloaded");
                        JOptionPane.showMessageDialog(null, "download completed");
		} else {
			//System.out.println("No file to download. Server replied HTTP code: " + responseCode);
                        JOptionPane.showMessageDialog(null, "No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
	}
}
