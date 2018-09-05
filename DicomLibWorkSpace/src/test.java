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
	
	public static byte[] getByteValue(int inTag, String[] inString)
	{
		int inVR = Integer.parseInt(inString[0]);
		
		byte[] abTag =  getIntTag2Btyes(inTag);
		
		byte[] abVR  = new byte[2];
		ByteBuffer.wrap(abVR).putShort((short)inVR);
	
		byte[] abValue =  getDouble2Btyes(Double.parseDouble(inString[1]));//getString2Bytes(inString[1]);
		byte[] abLenght = null;
		
		if(abValue.length>=0xFFFF)
		{
			 abLenght = getInt2Bytes(abValue.length);
			 abVR = getShort2Bytes(inVR);
		}else
			 abLenght = getShort2Bytes(abValue.length);
			
		int margeLength = abTag.length+abVR.length+abValue.length+abLenght.length;
		byte[] concatBytes =  ByteBuffer.allocate(margeLength).put(abTag).put(abVR).put(abLenght).put(abValue).array();
		
		return concatBytes;
		
	}
	
	public static byte[] arrayReviers(byte[] inBytes)
	{
		byte[] outBytes = new byte[inBytes.length];
		for(int i=0; i< inBytes.length;i++)
			outBytes[i] = inBytes[inBytes.length-1-i];
		return outBytes;
	}
	
	public static byte[] getIntTag2Btyes(int inInput)
	{    
		byte[] outBytes = new byte[4];
		ByteBuffer.wrap(outBytes).putInt(inInput);
		
		if(m_FlagLittle)
		{
			outBytes = arrayReviers(outBytes);
			return new byte[] {outBytes[2], outBytes[3], outBytes[0], outBytes[1]};
		}else	
			return outBytes;
		
	}
	
	public static byte[] getDouble2Btyes(double inInput)
	{    
		byte[] outBytes = new byte[8];
		ByteBuffer.wrap(outBytes).putDouble(inInput);
		
		if(m_FlagLittle)
		{
			return arrayReviers(outBytes);
		}else	
			return outBytes;
		
	}
	
	public static byte[] getFloat2Btyes(float inInput)
	{    
		byte[] outBytes = new byte[8];
		ByteBuffer.wrap(outBytes).putFloat(inInput);
		
		if(m_FlagLittle)
		{
			return arrayReviers(outBytes);
		}else	
			return outBytes;
		
	}
	
	
	
	public static byte[] getInt2Bytes(int inInput)
	{
		byte[] outBytes = new byte[4];
		ByteBuffer.wrap(outBytes).putInt(inInput);
		
		if(m_FlagLittle)
		{
			return arrayReviers(outBytes);
		}else	
			return outBytes;
	}
	
	
	public static byte[] getShort2Bytes(int inInput)
	{
		byte[] outBytes = new byte[2];
		ByteBuffer.wrap(outBytes).putShort((short)inInput);
		
		if(m_FlagLittle)
		{
			return arrayReviers(outBytes);
		}else	
			return outBytes;
	}
	
	public static byte[] getString2Bytes(String inString)
	{
		byte[] outBytes = inString.getBytes();
		if(outBytes.length%2!=0)
			outBytes = paddingZero(outBytes,outBytes.length+1);
		return outBytes;
	}
	
	private static byte[] paddingZero( byte[] inBytes, int inSize)
	{
		byte[] tmp = new byte[inSize];
		for(int i=0; i<inBytes.length;i++)
			tmp[i] = inBytes[i];
		for(int i=inBytes.length; i<inSize;i++)
			tmp[i] = 0x00;

		return tmp.clone();
	}
	
	

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
		testR.readDCMFile("D:\\98_data\\03_AiCRO_Dev\\DiocomLib\\CT_Base.dcm");
		AC_DcmStructure ss = null;
		try {
			ss = testR.getAttirbutes();
			System.out.println(ss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ss.printInfo("");
		
		
		
		AC_DicomWriter testW = new AC_DicomWriter(new File("D:\\98_data\\03_AiCRO_Dev\\DiocomLib\\CT_Base.txt"));
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
