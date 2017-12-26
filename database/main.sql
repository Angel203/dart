/*
Navicat SQLite Data Transfer

Source Server         : darts-db
Source Server Version : 30706
Source Host           : :0

Target Server Type    : SQLite
Target Server Version : 30706
File Encoding         : 65001

Date: 2017-12-26 22:02:55
*/

PRAGMA foreign_keys = OFF;

-- ----------------------------
-- Table structure for "main"."games"
-- ----------------------------
DROP TABLE "main"."games";
CREATE TABLE "games" (
"id"  INTEGER NOT NULL,
"gamedate"  TEXT(255) NOT NULL,
"p1_id"  INTEGER NOT NULL,
"p2_id"  INTEGER DEFAULT -1,
"p3_id"  INTEGER DEFAULT -1,
"p4_id"  INTEGER DEFAULT -1,
"P5_id"  INTEGER DEFAULT -1,
"P6_id"  INTEGER DEFAULT -1,
"set_id"  INTEGER NOT NULL,
PRIMARY KEY ("id" ASC),
CONSTRAINT "Player_ID_1" FOREIGN KEY ("p1_id") REFERENCES "player" ("id"),
CONSTRAINT "Player_ID_2" FOREIGN KEY ("p2_id") REFERENCES "player" ("id"),
CONSTRAINT "Player_ID_3" FOREIGN KEY ("p3_id") REFERENCES "player" ("id"),
CONSTRAINT "Player_ID_4" FOREIGN KEY ("p4_id") REFERENCES "player" ("id"),
CONSTRAINT "Player_ID_5" FOREIGN KEY ("P5_id") REFERENCES "player" ("id"),
CONSTRAINT "Player_ID_6" FOREIGN KEY ("P6_id") REFERENCES "player" ("id"),
CONSTRAINT "Set" FOREIGN KEY ("set_id") REFERENCES "sets" ("id")
);

-- ----------------------------
-- Table structure for "main"."player"
-- ----------------------------
DROP TABLE "main"."player";
CREATE TABLE "player" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT(50) NOT NULL,
"surname"  TEXT(50) NOT NULL,
"nickname"  TEXT(50) NOT NULL
);


-- ----------------------------
-- Table structure for "main"."sets"
-- ----------------------------
DROP TABLE "main"."sets";
CREATE TABLE "sets" (
"id"  INTEGER NOT NULL,
"wins_needed"  INTEGER NOT NULL,
PRIMARY KEY ("id" ASC)
);

-- ----------------------------
-- Table structure for "main"."sqlite_sequence"
-- ----------------------------
DROP TABLE "main"."sqlite_sequence";
CREATE TABLE sqlite_sequence(name,seq);


-- ----------------------------
-- Table structure for "main"."throws"
-- ----------------------------
DROP TABLE "main"."throws";
CREATE TABLE "throws" (
"id"  INTEGER NOT NULL,
"game_id"  INTEGER NOT NULL,
"player_id"  INTEGER NOT NULL,
"value"  INTEGER NOT NULL,
PRIMARY KEY ("id" ASC),
CONSTRAINT "Game_ID" FOREIGN KEY ("game_id") REFERENCES "games" ("id"),
CONSTRAINT "Player_ID" FOREIGN KEY ("player_id") REFERENCES "player" ("id")
);
