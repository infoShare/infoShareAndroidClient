package com.infoshare.model;

import java.io.Serializable;

public class AbstractIdentity implements Serializable {

	private static final long serialVersionUID = -4056785042925883798L;

	public class Id implements Serializable {
		
		private static final long serialVersionUID = 4182773129214080930L;
		
		long id;
		
		public Id(long id){
			this.id = id;
		}
		
		public long getValue(){
			return id;
		}

		@Override
		public int hashCode() {
			return 31 + (int) (id ^ (id >>> 32));
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			if (id != other.id)
				return false;
			return true;
		}
	}
}
