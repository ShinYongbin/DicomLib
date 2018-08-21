import java.io.IOException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import AC_DicomIO.AC_DicomDictionary;
import AC_DicomIO.AC_DicomReader;
import AC_DicomIO.AC_Tag;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AC_DicomReader testR = new AC_DicomReader();
		AC_DicomDictionary.setupList();
		testR.readDCMFile("D:\\98_data\\03_AiCRO_Dev\\DiocomLib\\CT_Base.dcm");
		
		try {
			String ss = testR.getTagValue(AC_Tag.PixelData);
			System.out.println(ss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		
		int b0 =(int) 0x55;
		int b1 =(int)  0x49;
		
		int m_VR = (b0<<8) + b1;
		
		System.out.println((char)b0+""+(char)b1);
		
		
		byte[] ba = new byte[4];
		ba[0]= (byte)(( m_VR >> 24 ) ); 
		ba[1]= (byte)(( m_VR >> 16 )); 
		ba[2]= (byte)(( m_VR >> 8 )); 
		ba[3]= (byte)( m_VR  );; 

		System.out.println((char)ba[2]+""+(char)ba[3]);
	}

}
