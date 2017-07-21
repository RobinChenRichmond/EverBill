import java.awt.FlowLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainApp {
	public static void main(String []args){
		List<People> pList = new ArrayList<>();

		//手动输入文件名
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Please enter the txt name: ");
//		String fileName = sc.next();
//		File file = new File(fileName);
		
		//JFrame选取文件
		JFrame frame = new JFrame();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		chooser.setFileFilter(filter);
		int ret = chooser.showOpenDialog(frame);
		File file = null;
		if (ret == JFileChooser.APPROVE_OPTION) {
		        file = chooser.getSelectedFile();
		        // dir is the selected directory
		}
		
		try {
			Scanner sf = new Scanner(file);
			String[] names = sf.nextLine().split(" ");

			for(int i = 0; i < names.length; i ++){
				People temp = new People(i,names.length,names[i]);
				pList.add(temp);
			}
			while(sf.hasNextLine()){
				int nameIndex = sf.nextInt()-1;
				double amount = sf.nextDouble();
				String data = sf.next();
				int[] needToPay = new int[names.length];
				if(data.equals("a")){
					amount = amount/(names.length-1);
					for(int i = 0; i < names.length; i++){
						if(i!=nameIndex){needToPay[i] = 1;}
					}
				} else{
					int payers = 0;
					for(int i = 0; i < names.length; i++){
						needToPay[i] = Character.getNumericValue(data.charAt(i));
						if(needToPay[i]>0){payers++;}
					}
					amount = amount/payers;
				}
				for(int j = 0; j < pList.size(); j++){
					if(needToPay[j]>0){
						double pureBill = Math.round((pList.get(nameIndex).getBill(j)+amount-pList.get(j).getBill(nameIndex))*100)/(double)100;
						if(pureBill>0){
							pList.get(nameIndex).setBill(j, pureBill);
							pList.get(j).setBill(nameIndex, 0);
						} else{
							pList.get(j).setBill(nameIndex, 0-pureBill);
							pList.get(nameIndex).setBill(j, 0);
						}
					}
					
				}
			}
			
			String text = "感谢使用Guanyu Inc.产品EverBill!\n本次计算费用$99/￥648\n\n简化账单：\n";
			
			JFrame result = new JFrame();
			result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			for(int n = 0; n < pList.size(); n++){
				for(int m = 0; m < pList.size(); m++){
					if(pList.get(m).getBill(n)>0){
						text += pList.get(n).getName()+" 付 "+pList.get(m).getName()+": "+pList.get(m).getBill(n)+"\n";
						//System.out.println(text);
					}
				}
			}
			JTextArea jt = new JTextArea(text);
			result.add(jt);
			result.setLayout(new FlowLayout());
			result.pack();
			
			result.setLocationRelativeTo(null);
			result.setVisible(true);
			sf.close();
			
			
		} catch(NullPointerException | FileNotFoundException e){
			JFrame result = new JFrame();
			result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel jl = new JLabel("没选文件！你到底会不会用？");
			result.add(jl);
			result.setLayout(new FlowLayout());
			result.pack();
			
			result.setLocationRelativeTo(null);
			result.setVisible(true);
			
			//e.printStackTrace();
		} catch (InputMismatchException e) {
			JFrame result = new JFrame();
			result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel jl = new JLabel("文件格式错误！微信转账￥50至作者账户即刻帮你修改格式！");
			result.add(jl);
			result.setLayout(new FlowLayout());
			result.pack();
			
			result.setLocationRelativeTo(null);
			result.setVisible(true);
			
			//e.printStackTrace();
			//System.exit(1);
		} 
		
		
	}
}
