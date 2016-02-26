package com.moneycontrol.handheld.chart.entity;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import com.moneycontrol.handheld.api.AppBeanParacable;

public class StickChartEntity implements AppBeanParacable {
	static NumberFormat nf = NumberFormat.getInstance();

	String name;
	String date_time;
	String current_close;
	String status;
	String direction;

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getCurrent_close() {
		return current_close;
	}

	public void setCurrent_close(String current_close) {
		this.current_close = current_close;
	}

	public String getPrev_close() {
		return prev_close;
	}

	public void setPrev_close(String prev_close) {
		this.prev_close = prev_close;
	}

	public ArrayList<Values> getList() {
		return list;
	}

	public void setList(ArrayList<Values> list) {
		this.list = list;
	}

	String prev_close;
	private ArrayList<Values> list = new ArrayList<Values>();

	public static class Values {

		String time;
		String value;
		String open;
		String high;
		String low;
		String close;
		String volume;
		String chg;
		String pchg;
		String dir;

		public void setValue(String value) {
			this.value = value;
		}

		public void setOpen(String open) {
			this.open = open;
		}

		public void setHigh(String high) {
			this.high = high;
		}

		public void setLow(String low) {
			this.low = low;
		}

		public void setClose(String close) {
			this.close = close;
		}

		public void setVolume(String volume) {
			this.volume = volume;
		}

		public void setChg(String chg) {
			this.chg = chg;
		}

		public void setPchg(String pchg) {
			this.pchg = pchg;
		}

		public float getChg() {
			try {
				return nf.parse(chg).floatValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public float getPchg() {
			try {
				return nf.parse(pchg).floatValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public String getDir() {
			return dir;
		}

		public void setDir(String dir) {
			this.dir = dir;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public float getValue() {
			try {
				if (value == null) {
					return 0;
				}
				return nf.parse(value).floatValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public float getOpen() {
			try {
				if (open == null) {
					return 0;
				}
				return nf.parse(open).floatValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public float getHigh() {
			try {
				if (high == null) {
					return 0;
				}
				return nf.parse(high).floatValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public float getLow() {
			try {
				if (low == null) {
					return 0;
				}
				return nf.parse(low).floatValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public float getClose() {
			try {
				if (close == null) {
					return 0;
				}
				return nf.parse(close).floatValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		public float getVolume() {
			try {
				if (volume == null) {
					return 0;
				}
				return nf.parse(volume).floatValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

	}
}
