import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
import javax.swing.plaf.synth.SynthFormattedTextFieldUI;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import AC_DicomIO.AC_DcmStructure;
import AC_DicomIO.AC_DicomDictionary;
import AC_DicomIO.AC_DicomReader;
import AC_DicomIO.AC_DicomWriter;
import AC_DicomIO.AC_Tag;


public class test {
	
	static Logger logger = Logger.getLogger(test.class);
	static boolean m_FlagLittle = true;
	
	public static void main(String[] args) {
	//	for(String tmp : args)
		/*{
			System.out.format("Short : %d ", Short.BYTES);
			System.out.format("Integer : %d ", Integer.BYTES);
			System.out.format("Float : %d ", Float.BYTES);
			System.out.format("Double : %d ", Double.BYTES);
			
	//	System.console().printf("%s", tmp);
		}
		
		byte[] test = new byte[0];
		byte[] test2 = new byte[4];
		
		System.out.println("Lenght :" + test.length );
		System.out.println("value :" + test );
		byte[]  outBytes = ByteBuffer.allocate(8).put(test2).put(test2).put(test).array();
	
		for(byte tmp : outBytes)
		{
			//System.out.print(idx++);;//("%x ", b);
			System.out.format("%02X ", tmp);//("%x ", b);
		}*/
		
		/*String test = "\\";
		String[] split = test.split("\\\\");
		int i=0;
		for(String tmp : split)
		{
			System.out.println("num : "+i++;
		   System.out.println(tmp);
		}*/
		
	
		PropertyConfigurator.configure("log4j.propertie");


		// TODO Auto-generated method stub
			AC_DicomReader testR = new AC_DicomReader();
		//AC_DicomDictionary.setupList();
		testR.readDCMFile("D:\\98_data\\03_AiCRO_Dev\\DiocomLib\\00000497");
		AC_DcmStructure ss = null;
		try {
			ss = testR.getAttirbutes();
			System.out.println(ss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss.Alnaysis(1);
		ss.printInfo("");
		
		
		
		AC_DicomWriter testW = new AC_DicomWriter(new File("D:\\98_data\\03_AiCRO_Dev\\DiocomLib\\00000497.txt"));
	
		testW.writeDCMFile(ss);
		
	
		
		//private byte[] getByteValue(int inTag, String[] inString)
		/*{
			
			int inTag = 0x00189306; 
			String[] inString = new String[2];
			inString[0] = Integer.toString(0x4644);
			inString[1] = Double.toString(0.625);
			int inVR = Integer.parseInt(inString[0]);
			
			
		

			
			
			
			byte[] abGroup =  getByteValue(inTag, inString);
		
			
			
			int idx = 0;
			for(byte tmp : abGroup)
			{
				//System.out.print(idx++);;//("%x ", b);
				System.out.format("%02X ", tmp);//("%x ", b);
			}
			
		
			
		}*/
		
		
	
		
		
		/*int b0 =(int) 0x55;
		int b1 =(int)  0x49;*/
		
		/*int b0 = 0xFF;
		int b1 = 0xFF;
		int b2 = 0xFF;
		int b3 = 0xFF;
		
		int m_VR = (b0<<8) + b1;
		
		System.out.println( ((b3<<24) + (b2<<16) + (b1<<8) + b0));
		
		
		byte[] ba = new byte[4];
		ba[0]= (byte)(( m_VR >> 24 ) ); 
		ba[1]= (byte)(( m_VR >> 16 )); 
		ba[2]= (byte)(( m_VR >> 8 )); 
		ba[3]= (byte)( m_VR  );; 

		System.out.println((char)ba[2]+""+(char)ba[3]);
		
		BasicConfigurator.configure();*/ 
		
		//logger.info("InfoTest");
	}
	
	
}
