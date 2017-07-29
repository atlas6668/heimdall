

/* --------------------------------------
-- Table structure for `wx_account`
-- appId 微信应用编码
-- secret 密码
-------------------------------------- */
DROP TABLE IF EXISTS wx_account; 
CREATE TABLE wx_account(
	id int(11) not null auto_increment,
	name varchar(200),
	appId varchar(200) not null,
	secret varchar(200) not null,
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

/* --------------------------------------
-- Table structure for `wx_autoresponse`
-------------------------------------- */
DROP TABLE IF EXISTS wx_autoresponse; 
CREATE TABLE wx_autoresponse(
	id int(11) not null auto_increment,
	category varchar(20),
	messageType varchar(20),
	content text,
	ruleName varchar(100),
	keyword varchar(100),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

/* --------------------------------------
-- Table structure for `wx_menu`
-------------------------------------- */
DROP TABLE IF EXISTS wx_menu; 
CREATE TABLE wx_menu(
	id int(11) not null auto_increment,
	mtype varchar(20),
	name varchar(100),
	mkey varchar(50),
	url varchar(500),
	father_button int(11),
	isOauth2Url INT(2),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

/* --------------------------------------
-- Table structure for `wx_qrcode`
-------------------------------------- */
DROP TABLE IF EXISTS wx_qrcode; 
CREATE TABLE wx_qrcode(
	id int(11) not null auto_increment,
	app varchar(200),
	name varchar(200),
	path varchar(200),
	scene varchar(200),
	wkey varchar(200),
	loadtimemills bigint(15),
	primary key(id)
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

-------------------------------------- */
