use aquila;
-- All hash are bcrypt('1234')
-- create 1 sysadmin
insert into users values (1, 'user1@email.com', 'John', '$2a$10$sdZ1fmtoFm4qkRmnP7SExetP4WNIrTnOqtrlNIEAc8Or.PkJjusCm', 'Doe', '3235551200', 'SYSADMIN', 'username1', null, null);


-- create 3 analysts
insert into users values (2, 'user2@email.com', 'Hauncho', '$2a$10$YxTMD3bkPJEk1EYCNRNLpeFLFtzTgrgpmnuZ0tNiwnSOafx1.5teW', 'Jack', '3235551111', 'UAS_ANALYST', 'username2', null, null);
insert into users values (3, 'user3@email.com', 'Jack', '$2a$10$HznMWFTonPX6ZjDfDcfZq.4CCnCqmSes10UOYJD6lzZy/WrKw5G/y', 'Doe', '3235552222', 'UAS_ANALYST', 'username3', null, null);
insert into users values (4, 'user4@email.com', 'Vince', '$2a$10$PvJIM0MvDmlmkHCf7vxVCeT77dicHQPS7VcFR/K/pMHbipP4QpuXe', 'Staples', '3235553333', 'UAS_ANALYST', 'username4', null, null);

-- create 6 investigators
insert into users values (5, 'user5@email.com', 'Ferg', '$2a$10$kOPbeFqDLmLp7Ld6MbkpN.BCf4hbm5dqot3ip9WWaLt7MGD1wTLg2', 'Yams', '3235554444', 'INVESTIGATOR', 'username5', null, null);
insert into users values (6, 'user6@email.com', 'Rocky', '$2a$10$CwHOWhSdw27z2aHNkNbOX.pvhjmOwXp9biI.XCkQJNVbcURSecqvG', 'Yams', '3235555555', 'INVESTIGATOR', 'username6', null, null);
insert into users values (7, 'user7@email.com', 'Austin', '$2a$10$08L5mfr31WD63qQyPcXkT.CmePvXpfhCCHx1Ub5GOYz3VdXXF1Kce', 'Post', '3235556666', 'INVESTIGATOR', 'username7', null, null);
insert into users values (8, 'user8@email.com', 'Swae', '$2a$10$9CZlrzruEQCiTG21Ziajde26rhRjXnhG3emVr5allwzMEjHraAZuq', 'Lee', '3235557777', 'INVESTIGATOR', 'username8', null, null);
insert into users values (9, 'user9@email.com', 'Slim', '$2a$10$EUmi6Xzdxg35zsAAiZpWreDX0eVb7Xz4fCbnrAjMuifigmVW7kTlm', 'Jimmy', '3235558888', 'INVESTIGATOR', 'username9', null, null);
insert into users values (10, 'user10@email.com', 'Cardi', '$2a$10$EVpPhxcUka/QuLO4vQzljOgdONzNkNGh9pfD8TPe5faCvKy.G83Hy', 'Bacardi', '3235559999', 'INVESTIGATOR', 'username10', null, null);