DROP DATABASE 
IF EXISTS tictactoe;

CREATE DATABASE 
IF NOT EXISTS tictactoe;

USE tictactoe;
CREATE TABLE IF NOT EXISTS players (
player_id int(11) NOT NULL AUTO_INCREMENT,
nickname varchar(45),
score int, 
PRIMARY KEY (player_id));

USE tictactoe;
CREATE TABLE IF NOT EXISTS games (
id int(11) NOT NULL AUTO_INCREMENT,
winner BOOLEAN DEFAULT FALSE,
time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id));