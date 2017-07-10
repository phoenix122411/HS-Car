package org.agile.constant;

/**
 * 常量
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public class Constant {
	
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
	
	/**
	 * 是或否
	 */
	public enum YesOrNo {
		No("0", "否"),
		Yes("1", "是");

		private String code;
		private String display;

		private YesOrNo(String code, String display) {
			this.code = code;
			this.display = display;
		}
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}

		public String getDisplay() {
			return display;
		}
		public void setDisplay(String display) {
			this.display = display;
		}
	}
	/**
	 * 根据YesOrNo的code来查找YesOrNo的枚举
	 */
	public static YesOrNo yesOrNo(String code) {
		YesOrNo[] enums = YesOrNo.values();
		for(int i=0; i<enums.length; i++) {
			if(enums[i].getCode().equals(code)) {
				return enums[i];
			}
		}
		return null;
	}
	
	/**
	 * 支持或不支持
	 */
	public enum SupportOrNot {
		NotSupport("0", "不支持"),
		Support("1", "支持");

		private String code;
		private String display;

		private SupportOrNot(String code, String display) {
			this.code = code;
			this.display = display;
		}
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}

		public String getDisplay() {
			return display;
		}
		public void setDisplay(String display) {
			this.display = display;
		}
	}
	/**
	 * 根据SupportOrNot的code来查找SupportOrNot的枚举
	 */
	public static SupportOrNot supportOrNot(String code) {
		SupportOrNot[] enums = SupportOrNot.values();
		for(int i=0; i<enums.length; i++) {
			if(enums[i].getCode().equals(code)) {
				return enums[i];
			}
		}
		return null;
	}

	/**
	 * 菜单类型
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}