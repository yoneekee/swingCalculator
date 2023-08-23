package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculSwing extends JFrame {

	/***** 클릭 이벤트 등의 함수처리를 위해 스윙 디자인 변수들 전역변수로 빼놓기 *****/
	Container contentPane;
	JPanel northPanel;
	JPanel centerPanel;
	JPanel southPanel;
	JTextField calculResult;
	JButton resetBtn;
	String txtFieldStr;

	/***** 계산기 버튼 노가다 *****/
	// 왜 배열과 for문을 이용하지 않는가
	// 클릭 이벤트 할 때 곤란해져서 그렇게 했다가 이렇게 뜯어 고침...ㅎ
	JButton calculNumsBtn00 = new JButton("1");
	JButton calculNumsBtn01 = new JButton("2");
	JButton calculNumsBtn02 = new JButton("3");
	JButton calculNumsBtn03 = new JButton("÷"); // ÷
	JButton calculNumsBtn04 = new JButton("4");
	JButton calculNumsBtn05 = new JButton("5");
	JButton calculNumsBtn06 = new JButton("6");
	JButton calculNumsBtn07 = new JButton("x"); // x
	JButton calculNumsBtn08 = new JButton("7");
	JButton calculNumsBtn09 = new JButton("8");
	JButton calculNumsBtn10 = new JButton("9");
	JButton calculNumsBtn11 = new JButton("-"); // -
	JButton calculNumsBtn12 = new JButton("0");
	JButton calculNumsBtn13 = new JButton(".");
	JButton calculNumsBtn14 = new JButton("+"); // +
	JButton calculNumsBtn15 = new JButton("=");

	/***** 텍스트 필드에 글자 띄우는 함수 *****/
	public void txtFieldAppend(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String buttonText = button.getText();
		String currentText = calculResult.getText();
		currentText += buttonText;
		calculResult.setText(currentText);
	}

	public String txtFieldEnd(ActionEvent e) {
		txtFieldStr = calculResult.getText();
		return txtFieldStr;
	}

	/***** 버튼 활성화 비활성화 *****/
	// 기호 하나를 선택하면 다른 계산 기호들까지 다 비활성화
	// 아직 두 가지 수에 대한 연산만 가능한 코드이므로 기호가 여러개가 필요 없음
	public void btnMarksUnabled() {
		calculNumsBtn03.setEnabled(false);
		calculNumsBtn07.setEnabled(false);
		calculNumsBtn11.setEnabled(false);
		calculNumsBtn14.setEnabled(false);
	}
	
	// 계산이 한 번 끝나고나면 모든 버튼이 비활성화 됨
	// 리셋 버튼을 누르면 모든 버튼이 활성화 됨
	public void btnEnabled(Boolean enabled) {
		calculNumsBtn00.setEnabled(enabled);
		calculNumsBtn01.setEnabled(enabled);
		calculNumsBtn02.setEnabled(enabled);
		calculNumsBtn03.setEnabled(enabled);
		calculNumsBtn04.setEnabled(enabled);
		calculNumsBtn05.setEnabled(enabled);
		calculNumsBtn06.setEnabled(enabled);
		calculNumsBtn07.setEnabled(enabled);
		calculNumsBtn08.setEnabled(enabled);
		calculNumsBtn09.setEnabled(enabled);
		calculNumsBtn10.setEnabled(enabled);
		calculNumsBtn11.setEnabled(enabled);
		calculNumsBtn12.setEnabled(enabled);
		calculNumsBtn13.setEnabled(enabled);
		calculNumsBtn14.setEnabled(enabled);
		calculNumsBtn15.setEnabled(enabled);
	}

	/******** 계산 함수 ********/
	// 기본적으로 모든 수를 더블로 가정
	public double doubleTypeCheck(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력 문자열이 유효하지 않습니다.");
        }

        // 문자열에 소수점이 있는지 검사
        if (input.contains(".")) {
            try {
                // 문자열을 double로 변환
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("유효한 double로 변환할 수 없는 문자열입니다.");
            }
        } else {
        	return Double.parseDouble(input);
        }
    }

	public double plus(double part1, double part2) {
		return part1 + part2;
	}

	public double minus(double part1, double part2) {
		return part1 - part2;
	}

	public double multiply(double part1, double part2) {
		return part1 * part2;
	}

	public double divide(double part1, double part2) {
		if (part2 == 0) {
			throw new ArithmeticException("0으로 나눌 수 없습니다.");
		}
		return part1 / part2;
	}

	public int resultCalcul(String txtFieldStr, ActionEvent e) {
		int result = 0;

		String[] parts; // 문자열을 공백으로 나눔
		String parts1Replace;

		if (txtFieldStr.contains(String.valueOf("x"))) {
			//System.out.println("x 있음!");
			parts = txtFieldStr.split("x");
			parts1Replace = parts[1].replace("=", "");
			parts[1] = parts1Replace;

			double part1 = doubleTypeCheck(parts[0]);
			double part2 = doubleTypeCheck(parts[1]);
			double calResult = multiply(part1, part2);
			System.out.println("part1 * part2 = " + calResult);

			String currentText = calculResult.getText();
			currentText += calResult;
			calculResult.setText(currentText);
		} else if (txtFieldStr.contains(String.valueOf("÷"))) {
			//System.out.println("÷ 있음!");
			parts = txtFieldStr.split("÷");
			parts1Replace = parts[1].replace("=", "");
			parts[1] = parts1Replace;

			double part1 = doubleTypeCheck(parts[0]);
			double part2 = doubleTypeCheck(parts[1]);
			double calResult = divide(part1, part2);
			System.out.println("part1 ÷ part2 = " + calResult);

			String currentText = calculResult.getText();
			currentText += calResult;
			calculResult.setText(currentText);
		} else if (txtFieldStr.contains(String.valueOf("-"))) {
			//System.out.println("- 있음!");
			parts = txtFieldStr.split("-");
			parts1Replace = parts[1].replace("=", "");
			parts[1] = parts1Replace;

			double part1 = doubleTypeCheck(parts[0]);
			double part2 = doubleTypeCheck(parts[1]);
			double calResult = minus(part1, part2);
			System.out.println("part1 - part2 = " + calResult);

			String currentText = calculResult.getText();
			currentText += calResult;
			calculResult.setText(currentText);
		} else if (txtFieldStr.contains(String.valueOf("+"))) {
			//System.out.println("+ 있음!");
			parts = txtFieldStr.split("\\+");
			parts1Replace = parts[1].replace("=", "");
			parts[1] = parts1Replace;

			double part1 = doubleTypeCheck(parts[0]);
			double part2 = doubleTypeCheck(parts[1]);
			double calResult = plus(part1, part2);
			System.out.println("part1 + part2 = " + calResult);

			String currentText = calculResult.getText();
			currentText += calResult;
			calculResult.setText(currentText);
		}

		return result;
	}

	/***** 생성자 안에 디자인 세팅 다 해놔서 
	 * 객체 생성만 해도 스윙 윈도우가 열리게 함*****/
	public CalculSwing() {
		/**** 처음 세팅 ****/
		this.setTitle("CALCULATOR");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/***** 전체 컨테이너는 BorderLayout의 구조를 가질 것 *****/
		contentPane = getContentPane();
		northPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();

		/***** NORTH : northPanel*****/
		calculResult = new JTextField();
		calculResult.setFont(new Font("Meiryou", Font.BOLD, 32));
		calculResult.setPreferredSize(new Dimension(600, 60));
		northPanel.add(calculResult);

		/***** CENTER : centerPanel*****/
		centerPanel.setLayout(new GridLayout(4, 4, 10, 10)); // 여백
		centerPanel.add(calculNumsBtn00);
		centerPanel.add(calculNumsBtn01);
		centerPanel.add(calculNumsBtn02);
		centerPanel.add(calculNumsBtn03);
		centerPanel.add(calculNumsBtn04);
		centerPanel.add(calculNumsBtn05);
		centerPanel.add(calculNumsBtn06);
		centerPanel.add(calculNumsBtn07);
		centerPanel.add(calculNumsBtn08);
		centerPanel.add(calculNumsBtn09);
		centerPanel.add(calculNumsBtn10);
		centerPanel.add(calculNumsBtn11);
		centerPanel.add(calculNumsBtn12);
		centerPanel.add(calculNumsBtn13);
		centerPanel.add(calculNumsBtn14);
		centerPanel.add(calculNumsBtn15);

		/***** SOUTH : southPanel*****/
		resetBtn = new JButton("C");
		resetBtn.setPreferredSize(new Dimension(680, 50));
		southPanel.add(resetBtn);

		/***** contentPane에 다 집어넣기*****/
		contentPane.add(northPanel, BorderLayout.NORTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		/***** 모든 디자인 코드가 끝난 뒤 마지막에 배치해야 하는 부분 *****/
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		/******* 클릭 이벤트에 따른 함수 처리 ******/
		// 리셋하기
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculResult.setText("");
				btnEnabled(true);
			};
		});

		// 버튼 하나 하나 노가다로 텍스트 필드에 값 띄우기 (마음에 들지 않지만)
		calculNumsBtn00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
				btnMarksUnabled();
			}
		});
		calculNumsBtn04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn05.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn06.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn07.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
				btnMarksUnabled();
			}
		});
		calculNumsBtn08.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn09.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
				btnMarksUnabled();
			}
		});
		calculNumsBtn12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
			}
		});
		calculNumsBtn14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
				btnMarksUnabled();
			}
		});
		calculNumsBtn15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldAppend(e);
				btnEnabled(false);
				String resultStr = txtFieldEnd(e);
				resultCalcul(resultStr, e);
			}
		});

	}

}
