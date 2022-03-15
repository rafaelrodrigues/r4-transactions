package com.r4.transaction.model;

import java.math.BigDecimal;



public enum OperationType {

	COMPRA_A_VISTA(1) {
		@Override
		public void calculateInput(Transaction t) {
			t.setAmount(t.getAmount().negate());
		}
	},
	COMPRA_PARCELADA(2) {
		@Override
		public void calculateInput(Transaction t) {
			t.setAmount(t.getAmount().negate());
		}
	},
	SAQUE(3) {
		@Override
		public void calculateInput(Transaction t) {
			t.setAmount(t.getAmount().negate());
		}
	},
	PAGAMENTO(4) {
		@Override
		public void calculateInput(Transaction t) {
			
		}
	};

	private final int typeCode;
	
	OperationType(int typeCode) {
		this.typeCode = typeCode;
	}
	
	public static OperationType forValue(final int typeCode) {
        for (final OperationType type: OperationType.values())
            if (type.typeCode == typeCode) return type;

        return null;
    }
	
	public static boolean exists(final int typeCode) {
		return forValue(typeCode)!=null;
	}

    public int toValue() {
        return this.typeCode;
    }
    
    public abstract void calculateInput(Transaction t);
}
