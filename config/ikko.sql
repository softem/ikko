-- テーブル(DROP)
DROP TABLE IF EXISTS business_report;
DROP TABLE IF EXISTS business_report_smmary;
DROP TABLE IF EXISTS commuter_ticket;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS holiday;
DROP TABLE IF EXISTS rest;
DROP TABLE IF EXISTS section;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS attendance_kind;
DROP TABLE IF EXISTS project;

-- マスタテーブル(CREATE)

-- 部署マスタ
CREATE TABLE `section` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SECTION_CODE` char(2) UNIQUE,  -- 部署コード
  `SECTION_NAME` varchar(40) NOT NULL,  -- 部署名
  `DELETE_FLAG` tinyint(1) DEFAULT 0,  -- 削除済みフラグ
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO section VALUES(1, '01', '総務', 0);
INSERT INTO section VALUES(2, '02', '開発', 0);

-- 権限マスタ
CREATE TABLE `authority` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTHORITY_CODE` char(2) UNIQUE,  -- 権限コード
  `SECTION_NAME` varchar(20) NOT NULL,  -- 権限名
  `DELETE_FLAG` tinyint(1) DEFAULT 0,  -- 削除済みフラグ
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO authority VALUES(1, '01', '管理者', 0);
INSERT INTO authority VALUES(2, '02', '一般', 0);

-- 勤怠区分マスタ
CREATE TABLE `attendance_kind` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATTENDANCE_KIND` char(2) UNIQUE,  -- プロジェクト勤怠区分
  `ATTENDANCE_KIND_NAME` varchar(20) NOT NULL,  -- 勤怠区分名
  `ATTENDANCE_KIND_MARK` char(2) NOT NULL,  -- 勤怠区分記号
  `DELETE_FLAG` tinyint(1) DEFAULT 0,  -- 削除済みフラグ
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 社員マスタ
CREATE TABLE `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_NO` int(5) UNIQUE,  -- 社員No
  `EMPLOYEE_NAME` varchar(20) NOT NULL,  -- 社員名
  `SECTION_ID` int(11) NOT NULL,  -- 部署ID
  `AUTHORITY_ID` int(11) NOT NULL,  -- 権限ID
  `LOGIN_ID` varchar(32) NOT NULL,  -- ログインID
  `PASSWORD` varchar(32) NOT NULL,  -- パスワード
  `DELETE_FLAG` tinyint(1) DEFAULT 0,  -- 削除済みフラグ
  PRIMARY KEY (`ID`),
  FOREIGN KEY (SECTION_ID) REFERENCES section(ID),
  FOREIGN KEY (AUTHORITY_ID) REFERENCES authority(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO employee VALUES(1, 0, 'ゲスト', 2, 2, 'guest', 'guest', 0);

-- プロジェクトマスタ
CREATE TABLE `project` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECT_CODE` char(20) UNIQUE,  -- プロジェクトコード
  `PROJECT_NAME` varchar(20) NOT NULL,  -- プロジェクト名
  `WORK_PLACE` varchar(20) NOT NULL,  -- 作業場所
  `FLEX_FLAG` tinyint(1) DEFAULT 0,  -- フレックスフラグ
  `CORE_TIME_START` time DEFAULT NULL,  -- コアタイム開始時刻
  `CORE_TIME_FINISH` time DEFAULT NULL,  -- コアタイム終了時刻
  `UNIT_MINUTE` int(2) NOT NULL,  -- 単位時間（分）
  `MONDAY` char(1) DEFAULT NULL,  -- 月曜日
  `TUESDAY` char(1) DEFAULT NULL,  -- 火曜日
  `WEDNESDAY` char(1) DEFAULT NULL,  -- 水曜日
  `THURSDAY` char(1) DEFAULT NULL,  -- 木曜日
  `FRIDAY` char(1) DEFAULT NULL,  -- 金曜日
  `SATURDAY` char(1) DEFAULT NULL,  -- 土曜日
  `SUNDAY` char(1) DEFAULT NULL,  -- 日曜日
  `FIX_START_TIME` time DEFAULT NULL,  -- 定時開始時刻
  `FIX_FINISH_TIME` time DEFAULT NULL,  -- 定時終了時刻
  `AFTER_FIX_START_TIME` time DEFAULT NULL,  -- 定時後開始時刻
  `AFTER_FIX_FINISH_TIME` time DEFAULT NULL,  -- 前日終了時刻
  `DELETE_FLAG` tinyint(1) DEFAULT 0,  -- 削除済みフラグ
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 休日マスタ
CREATE TABLE `holiday` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` int(11) NOT NULL,  -- プロジェクトID
  `HOLIDAY_DATE` date NOT NULL,  -- 休日日付
  `HOLIDAY_NAME` varchar(20) NOT NULL,  -- 休日名
  `DELETE_FLAG` tinyint(1) DEFAULT 0,  -- 削除済みフラグ
  PRIMARY KEY (`ID`),
  UNIQUE (PROJECT_ID,HOLIDAY_DATE),
  FOREIGN KEY (PROJECT_ID) REFERENCES project(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 休憩マスタ
CREATE TABLE `rest` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` int(11) NOT NULL,  -- プロジェクトID
  `REST_NO` int(11) NOT NULL,  -- 休憩マスタ連番
  `REST_START_TIME` time NOT NULL,  -- 休憩開始
  `REST_FINISH_TIME` time NOT NULL,  -- 休憩終了
  `DELETE_FLAG` tinyint(1) DEFAULT 0,  -- 削除済みフラグ
  PRIMARY KEY (`ID`),
  UNIQUE (PROJECT_ID,REST_NO),
  FOREIGN KEY (PROJECT_ID) REFERENCES project(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- トランテーブル(CREATE)

-- 業務日報情報（日単位）
CREATE TABLE `business_report` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,  -- 社員ID
  `BUSINESS_REPORT_DATE` date NOT NULL,  -- 業務日報年月日
  `PROJECT_ID` int(11) NOT NULL,  -- プロジェクトコード
  `ATTENDANCE_KIND_ID` int(11) DEFAULT NULL,  -- 勤怠区分ID
  `START_TIME` time NOT NULL,  -- 開始時間
  `FINISH_TIME` time NOT NULL,  -- 終了時間
  `FLEX_WORK_TIME` time DEFAULT NULL,  -- フレックス稼働時間
  `NORMAL_WORK_TIME` time DEFAULT NULL,  -- 非フレックス稼働時間
  `NORMAL_OVERTIME_WORK` time DEFAULT NULL,  -- 非フレックス残業時間
  `MIDNIGHT_OVERTIME_WORK` time DEFAULT NULL,  -- 深夜残業時間
  `LEGAL_HOLIDAY_WORK_TIME` time DEFAULT NULL,  -- 法定休出時間
  `LATE_EARLY_LEFT_OUT_TIME` time DEFAULT NULL,  -- 遅刻・早退・外出時間
  `OUT_START_TIME` time DEFAULT NULL,  -- 外出時間(開始)
  `OUT_FINISH_TIME` time DEFAULT NULL,  -- 外出時間(終了)
  `COMMENT` varchar(40) DEFAULT NULL,  -- 備考
  PRIMARY KEY (`ID`),
  UNIQUE (EMPLOYEE_ID,BUSINESS_REPORT_DATE),
  FOREIGN KEY (EMPLOYEE_ID) REFERENCES employee(ID),
  FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
  FOREIGN KEY (ATTENDANCE_KIND_ID) REFERENCES attendance_kind(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 業務日報集計情報
CREATE TABLE `business_report_smmary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,  -- 社員ID
  `BUSINESS_REPORT_MONTH` date NOT NULL,  -- 業務日報年月
  `FLEX_WORK_SUMMARY_DAY` time DEFAULT NULL,  -- フレックス稼働日数
  `TEMPORARY_FLEX_TIME_SUMMARY` time DEFAULT NULL,  -- 仮フレックス稼働合計時間
  `FLEX_STANDARD_TIME_SUMMARY` time DEFAULT NULL,  -- フレックス標準合計時間
  `FLEX_WORK_TIME_SUMMARY` time DEFAULT NULL,  -- フレックス稼働合計時間
  `FLEX_OVERTIME_WORK_SUMMARY` time DEFAULT NULL,  -- フレックス残業合計時間
  `NORMAL_WORK_TIME_SUMMARY` time DEFAULT NULL, -- 非フレックス稼働合計時間
  `NORMAL_OVERTIME_WORK_SUMMARY` time DEFAULT NULL, -- 非フレックス残業合計時間
  `STANDARD_TIME_SUMMARY` time DEFAULT NULL,  -- 標準時間
  `ACTUAL_WORK_TIME` time DEFAULT NULL,  -- 実働時間
  `OVERTIME_WORK_SUMMARY` time DEFAULT NULL,  -- 残業合計時間
  `MIDNIGHT_OVERTIME_WORK_SUMMARY` time DEFAULT NULL,  -- 深夜残業合計時間
  `HARF_HOLIDAY_TIME_SUMMARY` time DEFAULT NULL,  -- 半休取得合計時間
  `LEGAL_HOLIDAY_WORK_SUMMARY` time DEFAULT NULL,  -- 法定休出合計時間
  `ALL_OVERTIME_WORK_SUMMARY` time DEFAULT NULL, -- 総残業時間
  `EVENT_FLAG` tinyint(1) DEFAULT 0,  -- 社内イベントフラグ
  `MONTHLY_SEPARATE_FLAG` tinyint(1) DEFAULT 0,  -- 月末締めフラグ
  PRIMARY KEY (`ID`),
  UNIQUE (EMPLOYEE_ID,BUSINESS_REPORT_MONTH),
  FOREIGN KEY (EMPLOYEE_ID) REFERENCES employee(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 定期代
CREATE TABLE `commuter_ticket` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_ID` int(11) NOT NULL,  -- 社員ID
  `COMMUTER_TICKET_MONTH` date NOT NULL,  -- 定期代年月
  `COMMUTER_TICKET_NO` int(11) NOT NULL,  -- 連番
  `START_DATE` date NOT NULL,  -- 使用開始年月日
  `LINE_NAME` varchar(40) NOT NULL,  -- 路線名
  `STATION_NAME_START` varchar(40) NOT NULL,  -- 駅名(始点)
  `STATION_NAME_END` varchar(40) NOT NULL,  -- 駅名(終点)
  `TICKET_PRICE` int(6) NOT NULL,  -- 金額
  PRIMARY KEY (`ID`),
  UNIQUE (EMPLOYEE_ID,COMMUTER_TICKET_MONTH,COMMUTER_TICKET_NO),
  FOREIGN KEY (EMPLOYEE_ID) REFERENCES employee(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

