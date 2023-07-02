import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {


	static short instructionMemory[] = new short[1024]; // 16 bits each word
	static byte dataMemory[] = new byte[2048];  // 8 bits each word

	//64 General-Purpose Registers (GPRS)
	static byte registerFile [] = new byte[64];  // 8 bits each register

	// special registers
	static byte[] status = new byte[8] ;  // 8 bits   SREG
	static short pc = 0 ; // 16 bits

	// clock
	static int cycles = 1 ;


	//flags
	static boolean branchFlag = true ;
	static boolean branchFlag1 = true ;


	public static void read( ) {

		int n = 0 ; // number of instructions
		FileReader p;
		try {
			p = new FileReader("program.txt");
			BufferedReader br = new BufferedReader(p);

			String line = br.readLine() ;
			int k = 0 ;
			while(line != null) {

				line = k +": "+ line; 
				String[] instruction = line.split(" ");
				String InstructionTemp = "" ;


				if(instruction[1].equals("ADD")) {
					InstructionTemp += "0000" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("SUB")) {
					InstructionTemp += "0001" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("MUL")) {
					InstructionTemp += "0010" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("MOVI")) {
					InstructionTemp += "0011" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 

					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					short r2=  Short.parseShort(instruction[3]) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}

					InstructionTemp += (Integer.toBinaryString(r2));
					System.out.println(Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("BEQZ")) {
					InstructionTemp += "0100" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3] ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("ANDI")) {
					InstructionTemp += "0101" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3] ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("EOR")) {
					InstructionTemp += "0110" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("BR")) {
					InstructionTemp += "0111" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("SAL")) {
					InstructionTemp += "1000" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3] ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("SAR")) {
					InstructionTemp += "1001" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3] ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("LDR")) {
					InstructionTemp += "1010" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3] ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				if(instruction[1].equals("STR")) {
					InstructionTemp += "1011" ; 

					int r1=  Integer.parseInt(instruction[2].substring(1) ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r1).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r1));

					int r2=  Integer.parseInt(instruction[3] ) ; 
					for(int i = 0 ; i < 6 - Integer.toBinaryString(r2).length() ; i++ ) {
						InstructionTemp += "0";
					}
					InstructionTemp += (Integer.toBinaryString(r2));
				}

				instructionMemory[Integer.parseInt(instruction[0].charAt(0)+"")] = (short) Integer.parseInt(InstructionTemp , 2);

				System.out.print("InstructionMemory row " + Integer.parseInt(instruction[0].charAt(0)+"")+ " = ");
				System.out.println( instructionMemory[Integer.parseInt(instruction[0].charAt(0)+"")]);


				line= br.readLine();
				n++;

				k++;

			}
			br.close();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("program in assembly language should be program.txt");
		}

		init(n);
		//fetch();

	}



	public static void init(int n) {


		Queue<Short> fetched = new LinkedList<Short>();
		Queue<byte[]> decoded = new LinkedList<byte[]>();
		Queue<Short> oldPc = new LinkedList<Short>();
		Queue<Short> olderPc = new LinkedList<Short>();
		short executing =0;


		while(pc < n  || (decoded.peek()!= null && pc == n )  ) { //number of cycles =  3 + ((n − 1) ∗ 1)

			System.out.println("\nCycle "+ cycles);


			if(pc < n ) { // fetch
				oldPc.add(pc);
				fetched.add(fetch( ));
			}


			if(cycles > 1   && fetched.peek() != null) { //decode
				if(!branchFlag) {
					fetched.remove();
					oldPc.remove();
				}
				else {
					olderPc.add(oldPc.peek());
					decoded.add( decode(fetched.remove() , oldPc.remove()  ) )  ;
				}

			}


			if(cycles > 2  && decoded.peek() != null) { //execute
				if(!branchFlag ) {
					decoded.remove();
					olderPc.remove();
					branchFlag = true ;
				}else if(branchFlag  && !branchFlag1) {
					branchFlag1= true;
				}
				else {
					execute(decoded.remove() , olderPc.remove() ) ;
				}
			}

			// memory ( ) ;
			// w r i t e b a c k ( ) ;

			cycles++;
		}

		System.out.println("");
		System.out.println("After the last cycle");
		//last clock 
		System.out.println("Pc register = "+pc);

		System.out.print("Status register = ");
		for(int i = status.length-1 ; i >= 0 ; i--) {
			System.out.print(status[i]);
		}
		System.out.println("");


		System.out.println("General Registers");
		for(int i =0 ; i < registerFile.length ; i++) { // g register
			System.out.print( "R"+i + " = " + registerFile[i]+ "  "  );
			if(i% 10 == 0 && i!=0)
				System.out.println("");
		}
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------");

		System.out.println("instructionMemory");
		for(int i =0 ; i < instructionMemory.length ; i++) {
			System.out.print("["+i+"] = "+ instructionMemory[i] + "  ");
			if(i% 10 == 0 && i!=0)
				System.out.println("");
		}

		System.out.println("");
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("");

		System.out.println("dataMemory");
		for(int i =0 ; i < dataMemory.length ; i++) {
			System.out.print("["+i+"] = "+ dataMemory[i] + "  ");
			if(i% 10 == 0 && i!=0)
				System.out.println("");
		}
		System.out.println("");

	} 
	public static short fetch( ) {
		short instruction = instructionMemory[pc]; 

		System.out.println("Fetching Instruction  : "+ pc );
		if(pc <  instructionMemory.length) {
			pc++;
		}
		else {
			pc= 0 ;
		}

		return instruction;

	}

	public static byte[] decode(short instruction , int pc ){

		System.out.println("Decoding Instruction  : "+ pc  +
				" with input parametars : "+  instruction  );

		byte opcode = 0;  // bits15:12
		byte r1 = 0;      // bits11:5
		byte r2 = 0;      // bits5:0
		byte imm = 0;     // bits5:0

		byte valueR1 = 0;
		byte valueR2 = 0;

		opcode = (byte) ((instruction & 0b1111000000000000) >>> 12) ;
		r1 = (byte) ((instruction & 0b0000111111000000) >>> 6) ;
		r2 = (byte) (instruction & 0b0000000000111111);
		imm = (byte) (instruction & 0b0000000000111111);



		valueR1 = registerFile[r1];

		if(opcode== 0 || opcode ==1 || opcode== 2 || opcode ==6 || opcode== 7 ) {


			valueR2 =registerFile[r2];
			byte[] result = { opcode ,r1 ,valueR1 , valueR2 };
			return result ;
		}
		else {
			byte[] result =  {opcode , r1,  valueR1 , imm };
			return result ;
		}





	}

	public static void execute(byte[] sad , short oldPc ) {

		System.out.println( "Executing Instruction "+ oldPc +
				" with input parametars: "+"opcode = "+ sad[0] + ", dst reg = "+ sad[1] 
						+ ", 1st reg old value = "+ sad[2] +", old 2nd value = "+ sad[3]  );

		byte opcode = sad[0]  ;
		byte r1 = sad[1]  ;
		byte valueR1 = sad[2]  ;
		byte valueR2 = sad[3]  ; 


		if (!branchFlag) {
			return ;
		}

		status = new byte[8] ;

		switch (opcode) {
		case 0: 
			int tempc1 = valueR1 & 0x000000FF;
			byte tempo1 = (byte) (valueR1 & 0b01111111); 
			valueR1=(byte) (valueR1+valueR2);
			if(valueR1==0)
				status[0]=1;
			else
				status[0]=0;
			int tempc2 = valueR2 & 0x000000FF;                    
			int mask=0b100000000;
			if( ((tempc1 + tempc2) & mask) == mask)                      //carry.
				status[4] = 1;
			else 
				status[4] = 0;

			mask=0b10000000;
			byte tempo2 = (byte) (valueR2 &0b01111111); 
			if((status[4]^((tempo1 + tempo2) & mask)>>>7)==1 ){          //xor for overflow.

				status[3]=1;

			}
			else
				status[3]=0;

			if(valueR1<0) // negative flag 
				status[2]=1;
			else
				status[2]=0;
			status[1]=(byte) (status[2]^status[3]);
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );

			break;

		case 1: //Subtract
			tempo1 = (byte) (valueR1 & 0b01111111); 
			valueR1=(byte) (valueR1-valueR2);

			if(valueR1==0)                    //zero flag.
				status[0]=1;
			else
				status[0]=0;

			mask=0b100000000;
			tempo2 = (byte) (valueR2 &0b01111111); 
			if((status[4]^((tempo1 + tempo2) & mask)>>>7)==1 ){          //xor for overflow.

				status[3]=1;

			}
			else
				status[3]=0;

			if(valueR1<0)                                            //negative flag.
				status[2]=1;
			else
				status[2]=0;
			status[1]=(byte) (status[2]^status[3]);        //sign flag.
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );
			break;

		case 2:// Multiply
			valueR1=(byte) (valueR1*valueR2);
			if(valueR1<0)                             //-ve flag.                       //negative flag.
				status[2]=1;
			else
				status[2]=0;
			if(valueR1==0)        //zero flag.
				status[0]=1;
			else
				status[0]=0;
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );
			break;

		case 3: //Move Immediate 
			valueR1=valueR2;
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );
			break;

		case 4: //Branch if Equal Zero
			if(valueR1==0){
				pc = (short) (valueR2 + oldPc +1) ;
				branchFlag = false ;
				branchFlag1 = false ;
			}
			System.out.println( "Pc new value = "+ pc );
			break;

		case 5: //And Immediate

			valueR1 = (byte) (valueR1 & valueR2);
			if(valueR1 <0 ){
				status[2]= 1;
			}
			else{
				status[2]=0;
			}
			if(valueR1==0){
				status[0]=1;
			}
			else{
				status[0]=0;
			}
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );
			break;

		case 6: // Exclusive Or
			valueR1 = (byte) (valueR1 ^ valueR2);
			if(valueR1 <0 ){
				status[2]= 1;
			}
			else{
				status[2]=0;
			}
			if(valueR1==0){
				status[0]=1;
			}
			else{
				status[0]=0;
			}
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );
			break;

		case 7:  // branch 
			String newPC = "" ;

			String strR1 = Integer.toBinaryString(valueR1);
			int tempLen = 8- strR1.length() ;
			for(int i =0; i < tempLen ; i ++) {
				strR1 = "0" + strR1 ;
			}

			String strR2 = Integer.toBinaryString(valueR2);
			tempLen = 8- strR2.length() ;
			for(int i =0; i < tempLen ; i ++) {
				strR2 = "0" + strR2 ;
			}

			newPC += strR1 +""+ strR2 ; //PC = R1 || R2 
			pc = (short) Integer.parseInt(newPC,2)  ;   
			branchFlag = false ;
			branchFlag1 = false ;
			System.out.println( "Pc new value = "+ pc );
			break;

		case 8:  // shift lift 
			valueR1 = (byte) (valueR1 << valueR2) ;  // R1 = R1 << IMM

			if(valueR1<0) // negative flag 
				status[2]=1;
			if(valueR1 == 0) // zero flag 
				status[0]=1;
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );
			break;

		case 9: // shift right

			byte msb = (byte) ((valueR1 & 10000000)>>7) ;  // most bit
			String temp = "" ;

			for(int i = valueR2 ; i> 0; i --) { 
				temp+= msb ;
			}
			temp+= (valueR1 >>> valueR2) ;

			valueR1 = Byte.valueOf(temp);

			if(valueR1<0) // negative flag 
				status[2]=1;
			if(valueR1 == 0) // zero flag 
				status[0]=1;
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );
			break;

		case 10: //Load to Register
			valueR1=dataMemory[valueR2];
			System.out.println( "dst register "+ r1 +" new value = "+ valueR1 );
			break;

		case 11:  //Store from Register
			dataMemory[valueR2]=valueR1;
			System.out.println( "dst data memory "+ valueR2 +" new value = "+ valueR1 );
			break;
		default : break ;
		}
		registerFile[r1]= valueR1 ;

		System.out.print("Status register = ");
		for(int i = status.length-1 ; i >= 0 ; i--) {
			System.out.print(status[i]);
		}
		System.out.println("");


	}

	public static void main(String[] args) {

		read();




	}




}
