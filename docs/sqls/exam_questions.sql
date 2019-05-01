-- 题库中心
create database exam_questions;
use exam_questions;

/* Build Table Structure */
CREATE TABLE et_knowledge_point
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	point_name VARCHAR(100) NOT NULL,
	description VARCHAR(500) NULL,
	is_active BOOLEAN DEFAULT 1 NOT NULL,
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	subject_id INTEGER
		COMMENT '题库ID' NOT NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE et_knowledge_point COMMENT = '知识点';


/******************** Add Table: et_question ************************/

/* Build Table Structure */
CREATE TABLE et_question
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(40)
		COMMENT '题目缩写' NOT NULL,
	content TEXT
		COMMENT '问题内容，json数据' NOT NULL,
	duration INTEGER
		COMMENT '答题时间' NULL,
	points INTEGER
		COMMENT '分值' NULL,
	answer TEXT
		COMMENT '答案' NOT NULL,
	difficulty INTEGER DEFAULT 1
		COMMENT '难度系数' NOT NULL,
	analysis TEXT
		COMMENT '解析' NOT NULL,
	reference VARCHAR(1000)
		COMMENT '来源' NULL,
	examing_point VARCHAR(5000)
		COMMENT '考点' NULL,
	keyword VARCHAR(5000)
		COMMENT '关键词' NULL,
	creator_id INTEGER
		COMMENT '创建人ID' NOT NULL,
	creator_name VARCHAR(100)
		COMMENT '创建人' NULL,
	question_type_id INTEGER NOT NULL,
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	is_active BOOLEAN DEFAULT 1 NOT NULL
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE et_question COMMENT = '试题';


/******************** Add Table: et_question_point_links ************************/

/* Build Table Structure */
CREATE TABLE et_question_point_links
(
	question_id INTEGER NOT NULL,
	point_id INTEGER NOT NULL
) ENGINE=InnoDB;

/* Add Primary Key */
ALTER TABLE et_question_point_links ADD CONSTRAINT pk_et_question_point_links
	PRIMARY KEY (question_id, point_id);


/******************** Add Table: et_question_tag ************************/

/* Build Table Structure */
CREATE TABLE et_question_tag
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	tag_name VARCHAR(100)
		COMMENT '标签名称' NOT NULL,
	description VARCHAR(500)
		COMMENT '描述' NULL,
	is_active BOOLEAN DEFAULT 1 NOT NULL,
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE et_question_tag COMMENT = '问题标签';


/******************** Add Table: et_question_tag_links ************************/

/* Build Table Structure */
CREATE TABLE et_question_tag_links
(
	tag_id INTEGER NOT NULL,
	question_id INTEGER NOT NULL
) ENGINE=InnoDB;


/******************** Add Table: et_question_type ************************/

/* Build Table Structure */
CREATE TABLE et_question_type
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(100) NULL,
	is_active BOOLEAN DEFAULT 1 NOT NULL,
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE et_question_type COMMENT = '题型';


/******************** Add Table: et_subjects ************************/

/* Build Table Structure */
CREATE TABLE et_subjects
(
	id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(50)
		COMMENT '题库名称' NOT NULL,
	description VARCHAR(100) COMMENT '描述' NULL,
	pId INTEGER
		COMMENT '父级题库' NULL,
	is_active BOOLEAN DEFAULT 1 NOT NULL,
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

/* Add Comments */
ALTER TABLE et_subjects COMMENT = '题库';
