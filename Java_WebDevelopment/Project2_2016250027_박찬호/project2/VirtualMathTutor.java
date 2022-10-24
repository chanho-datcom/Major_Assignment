package project2;

/*
Author: 박찬호
E-mail: qkrcksgh156@gmail.com
Course: Java Programming
Assignment: Programming Assignment 2
Due date: 06/03/2020
File: VirtualMathTutor.java
Compiler/IDE: Java SE Development Kit 8u191/IntelliJ IDEA
Operating system: MS Windows 10
*/



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VirtualMathTutor{ /*frame, textfield, panel, label,label2,label3생성하고 스트링배열, 각종 변수들을 선언해주었다.*/

    JTextField text;
    int num1 = (int)(Math.random()*10+1);
    int num2 = (int)(Math.random()*10+1);
    int num3 = (int)(Math.random()*3);
    String[] operater = new String[]{"plus", "minus", "times"};
    JLabel label = new JLabel ("How much is" + num1 + operater[num3] + num2);
    JLabel label2 = new JLabel("Please enter your answer" );
    JLabel label3 = new JLabel("2016250027 박찬호" );
    JPanel panel = new JPanel();
    String gt;
    int useranswer, ot, count;
    int a, b, c;
    JFrame frame = new JFrame();


    public static  void main(String[] args){ /*gui를 생성하고 실행한다*/

        VirtualMathTutor gui = new VirtualMathTutor();
        gui.go();
    }
    public void go(){

        panel.setLayout(null);  /*panel에 레이아웃에 null값을 주어 원하는 대로 배치할수 있게 하였다.*/
        panel.setBackground(Color.cyan);/*panel에 배경색을 주었다*/
        label.setSize(300, 40);   /*label에 사이즈와 위치를 지정하고 panel에 추가하였다.*/
        label.setLocation(120, 5);
        panel.add(label);

        label2.setSize(300, 40);/*label2에 사이즈와 위치를 지정하고 panel에 추가하였다.*/
        label2.setLocation(120, 40);
        panel.add(label2);

        label3.setSize(300, 40); /*label3에 사이즈와 위치를 지정하고 panel에 추가하였다.*/
        label3.setLocation(120, 170);
        panel.add(label3);

        text = new JTextField(20);/*택스트 필드 길이와 크기, 높이, 위치를 지정해주고 panel에 추가하였다.*/
        panel.add(text);
        text.setBounds(200,85,100,40);

        JButton button = new JButton("New Problem");/*버튼에 new problem이라는 단어를 넣어주고 버튼이 눌리면 generating이 행동한다*/
        button.addActionListener(new generating());

        button.setSize(150, 20);/* 버튼 사이즈와 위치를 지정하고 panel에 추가*/
        button.setLocation(200, 150);
        panel.add(button);
        text.addActionListener(new checking());/*enter키를 눌렀을때 checking이 행동한다.*/

        frame.setContentPane(panel);
        frame.setSize(500,250);/* 프레임 사이즈 지정*/
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    class generating implements ActionListener{/* 버튼이 눌렸을 때 */
        public void actionPerformed(ActionEvent ev) {
            generateProblem();
        }

    }
    public void generateProblem(){/*난수 3개가 a,b,c에 들어가고 label과 label2에 문구를 아래와 같이 바꾼다. textfield를 클리어한다.*/
        int n1 = (int)(Math.random()*10+1);
        a = n1;
        int n2 = (int)(Math.random()*10+1);
        b = n2;
        int n3 = (int)(Math.random()*3);
        c = n3;
        label.setText("How much is"+ n1+ operater[n3] + n2);
        label2.setText("Please enter your answer");
        text.setText("");
    }
    class checking implements ActionListener{/*enter키를 눌렀을 때*/
        public void actionPerformed(ActionEvent ev) {/*count가 1씩 증가하며 gt에 textfield에 입력된 값이 들어가고 useranswer에 int타입으로 다시 저장된다.*/

            ++count;
            gt = text.getText();
            useranswer = Integer.parseInt(gt);
            CheckAnswer(useranswer);/* CHECKANSWER메소드가 이용되고 text field값이 클리어된다.*/
            text.setText("");
        }
    }
    public void CheckAnswer (int useranswer){/* c라는 난수를 생성되어 더하기, 빼기,곱하기 중 어떤 것을 수행할지 결정한다.*/
        if(c == 0){
            ot = a + b;
        }else if(c == 1){
            ot = a - b;
        }else{
            ot = a*b;
        }
        if(ot == useranswer){/* 정답이 맞을 경우*/
            label2.setText("Very good! It only took you" + count + "try");
            count = 0;
        }else{/*틀리거나 이상한 값을 입력할 경우*/
            label2.setText("I'm sorry, but no. Please try again.");
        }
    }
}
