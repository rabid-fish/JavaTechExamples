package com.github.rabid_fish;

public class MathOperationRowFixture extends fit.RowFixture {

	public static class MathOperationRow {

		private int arg1;
		private int arg2;
		private int result;
		
		public MathOperationRow(int arg1, int arg2, int result) {
			this.arg1 = arg1;
			this.arg2 = arg2;
			this.result = result;
		}
		
		public int getArg1() {
			return arg1;
		}

		public int getArg2() {
			return arg2;
		}

		public int getResult() {
			return result;
		}
	}

	public Class<?> getTargetClass() {
		return MathOperationRow.class;
	}

	@Override
	public Object[] query() throws Exception {
		
		MathOperationRow row1 = new MathOperationRow(4, 4, 8);
		MathOperationRow row2 = new MathOperationRow(3, 5, 8);
		MathOperationRow row3 = new MathOperationRow(6, 9, 15);
		
		return new MathOperationRow[] { row1, row2, row3 };
	}

}
