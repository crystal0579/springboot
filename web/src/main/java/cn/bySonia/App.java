package cn.bySonia;

/**
 * Hello world!
 */
public class App {

	public static void main(String[] args) {
		int manNum = 0;
		int womanNum = 0;

		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < 12; j++) {
				if (((i * 4 + j * 3 + (36 - i - j) / 2) == 36) && ((36 - i - j) % 2 == 0)) {
					// 注意：孩子的人数必须是偶数，否则会出现一个孩子一次也没有搬的情况，不符合题意
					manNum = i;
					womanNum = j;
					System.out.println("男的的人数是" + manNum);
					System.out.println("女的的人数是" + womanNum);
					System.out.println("孩子的人数是" + (36 - manNum - womanNum));
				}
			}
		}
	}
}
