-- sudo -u postgres psql -d siimon -f testidInitDB.sql

set schema 'testid';

INSERT INTO lang_level (id, value, deleted) VALUES
(1, 'A1', false),
(2, 'A2', false),
(3, 'B1', false),
(4, 'B2', false),
(5, 'C1', false),
(6, 'C2', false),
(7, 'general', false),
(9, 'A1.true', false),
(10, 'A1.2', false),
(11, 'A2.true', false),
(12, 'A2.2', false),
(13, 'B1.true', false),
(14, 'B1.2', false),
(15, 'B2.true', false),
(16, 'B2.2', false),
(17, 'C1.true', false),
(18, 'C1.2', false),
(19, 'C2.true', false),
(20, 'C2.2', false),
(21, 'A', false),
(22, 'B', false),
(23, 'C', false);

INSERT INTO test (id, title, description, protected_key, protected, deleted, disabled) VALUES
(1, 'Eesti keele üldine tasemetest.', 'Test määramaks inimese keeletaset.', 'TEST', true, false, false);

INSERT INTO part (id, description, max_time, step, deleted, disabled) VALUES
(1, 'A-tasemeosa.', '00:10:00', 1, false, false),
(2, 'B-tasemeosa.', '00:15:00', 2, false, false),
(3, 'C-tasemeosa', '00:05:00', 3, false, false);

INSERT INTO test_parts (id, test_id, part_id, deleted, disabled) VALUES
(1, 1, 1, false, false),
(2, 1, 2, false, false),
(3, 1, 3, false, false);


INSERT INTO grade (id, part_id, lang_level_id, min_result_percent, max_result_percent, next_part, deleted) VALUES
(1, 1, 1, 0.00, 39.00, false, false),
(2, 1, 2, 40.00, 79.00, false, false),
(3, 1, 21, 80.00, 100.00, true, false),
(4, 2, 3, 0.00, 39.00, false, false),
(5, 2, 4, 40.00, 79.00, false, false),
(6, 2, 22, 80.00, 100.00, true, false),
(7, 3, 22, 0.00, 79.00, false, false),
(8, 3, 23, 80.00, 100.00, true, false);


INSERT INTO question (id, part_id, max_time, question, points, deleted, disabled) VALUES
(1, 1, NULL, 'Minu nimi ___ Mart.', 1.00, false, false),
(2, 1, NULL, 'Ma töötan ___ .', 1.00, false, false),
(3, 1, NULL, 'Koht, kus saab vaadata filme.', 1.00, false, false),
(4, 1, NULL, 'Kui pikk on teie tööpäev?', 1.00, false, false),
(5, 1, NULL, 'Kust te pärit olete?', 1.00, false, false),
(6, 1, NULL, 'Missugune on teie perekond?', 1.00, false, false),
(7, 1, NULL, 'Millega te sõidate tööle?', 1.00, false, false),
(8, 1, NULL, 'Kas sa ___ ?', 1.00, false, false),
(9, 1, NULL, 'Mitmekesi te selles majas elate?', 1.00, false, false),
(10, 1, NULL, 'Kus te sööte lõunat? Me sööme lõunat ___', 1.00, false, false),
(11, 1, NULL, 'Mis kuupäev täna on? Täna on ___ .', 1.00, false, false),
(12, 1, NULL, 'Kas sa ___ ?', 1.00, false, false),
(13, 1, NULL, 'Arvutit ___ tänapäeval palju.', 1.00, false, false),
(14, 1, NULL, 'Õunad ___ .', 1.00, false, false),
(15, 1, NULL, 'Meil on puhkus ___ .', 1.00, false, false),
(16, 1, NULL, 'Tahan osta tolmuimejat. Kus on ___ kauplus?', 1.00, false, false),
(17, 1, NULL, '___ elab teie õde?', 1.00, false, false),
(18, 1, NULL, 'Eelmisel aastal nad ___ Paides.', 1.00, false, false),
(19, 1, NULL, 'Söö rohkem puu- ja juurvilja. See on ___ sinu tervisele.', 1.00, false, false),
(20, 1, NULL, 'Maja ___ on kauplus.', 1.00, false, false),
(21, 2, NULL, 'Me sõidame bussiga ___ teed.', 1.00, false, false),
(22, 2, NULL, 'Kas ma saaksin ___ härra Tammega?', 1.00, false, false),
(23, 2, NULL, 'Tort on taldriku ___ .', 1.00, false, false),
(24, 2, NULL, 'Õde on ___ kui vend.', 1.00, false, false),
(25, 2, NULL, 'Ta peab lahkuma ___ kell viis.', 1.00, false, false),
(26, 2, NULL, 'Sooja ilmaga me ___ limonaadi.', 1.00, false, false),
(27, 2, NULL, 'Turistid võivad minna oma ___ .', 1.00, false, false),
(28, 2, NULL, 'Ära ___ üle tee!', 1.00, false, false),
(29, 2, NULL, '___ on kõige rohkem raha?', 1.00, false, false),
(30, 2, NULL, '___ teevad linnud oma pesa?', 1.00, false, false),
(31, 2, NULL, 'Unustasin mütsi ___ .', 1.00, false, false),
(32, 2, NULL, 'Suitsupääsuke on Eesti ___ .', 1.00, false, false),
(33, 2, NULL, 'Haiglas oli palju ___ .', 1.00, false, false),
(34, 2, NULL, 'Rakvere on suur linn, aga Tartu on ___ .', 1.00, false, false),
(35, 2, NULL, 'Piim on juba ___ .', 1.00, false, false),
(36, 2, NULL, 'Läksin raamatut ___ tööle.', 1.00, false, false),
(37, 2, NULL, 'Mida te ___ ?', 1.00, false, false),
(38, 2, NULL, 'Läksin peole ___ .', 1.00, false, false),
(39, 2, NULL, 'Õues mäena, toas veena?', 1.00, false, false),
(40, 2, NULL, 'Ostsin emale ___ ja läksin kohe koju.', 1.00, false, false),
(41, 2, NULL, 'Vanasti ___ jala, nüüd sõidetakse autoga.', 1.00, false, false),
(42, 2, NULL, 'Aita ___ !', 1.00, false, false),
(43, 2, NULL, 'Käisime eile metsas maasikaid ___ .', 1.00, false, false),
(44, 2, NULL, 'Kui lapsed ___ , hakkasime tuba koristama.', 1.00, false, false),
(45, 2, NULL, 'Sellest poisist kasvas ___ .', 1.00, false, false),
(46, 2, NULL, 'Dispetšer koostas ___ eelmisel nädalal.', 1.00, false, false),
(47, 2, NULL, 'Kus ___ ilusaid lilli?', 1.00, false, false),
(48, 2, NULL, 'Ma istun ___ reas.', 1.00, false, false),
(49, 2, NULL, 'Minu vanavanemad ___ lambaid.', 1.00, false, false),
(50, 2, NULL, 'Eile ma ei ___ ühtegi tassi kohvi', 1.00, false, false),
(51, 3, NULL, '\"Mul ei ole sellest sooja ega külma\". See tähendab, et ___ .', 1.00, false, false),
(52, 3, NULL, 'Vanaema käis arsti ___ .', 1.00, false, false),
(53, 3, NULL, 'Kurt on inimene, kes ___ .', 1.00, false, false),
(54, 3, NULL, 'Ma ei leia oma rahakotti, see on ___ .', 1.00, false, false),
(55, 3, NULL, 'Täna oli bussis ___ .', 1.00, false, false),
(56, 3, NULL, 'Kas te olete õpetajana ___ ?', 1.00, false, false),
(57, 3, NULL, 'Klassijuhataja ütles ___ , et nad aitaksid teda.', 1.00, false, false),
(58, 3, NULL, 'Kas sa oled ___ väsinud?', 1.00, false, false),
(59, 3, NULL, 'Ta on targem kui sina. Ta on ___ targem.', 1.00, false, false),
(60, 3, NULL, 'Täname testi täitmise ___ !', 1.00, false, false);


INSERT INTO answer (id, question_id, answer, correct, deleted, disabled) VALUES
(1, 1, 'olema', false, false, false),
(2, 1, 'oleb', false, false, false),
(3, 1, 'on', true, false, false),
(4, 1, 'olla', false, false, false),
(5, 2, 'ehitajaks', false, false, false),
(6, 2, 'ehitajale', false, false, false),
(7, 2, 'ehitajana', true, false, false),
(8, 2, 'ehitaja', false, false, false),
(9, 3, 'autoprandus', false, false, false),
(10, 3, 'kino', true, false, false),
(11, 3, 'teater', false, false, false),
(12, 3, 'apteek', false, false, false),
(13, 4, '8  kuud', false, false, false),
(14, 4, '8 tundi', true, false, false),
(15, 4, '8 nädalat', false, false, false),
(16, 4, '8 päeva', false, false, false),
(17, 5, 'Venemaalt', true, false, false),
(18, 5, 'Venemaa', false, false, false),
(19, 5, 'Venemaas', false, false, false),
(20, 5, 'Venemaast', false, false, false),
(21, 6, 'pikk', false, false, false),
(22, 6, 'lai', false, false, false),
(23, 6, 'lühike', false, false, false),
(24, 6, 'suur', true, false, false),
(25, 7, 'trammis', false, false, false),
(26, 7, 'trammiga', true, false, false),
(27, 7, 'trammilt', false, false, false),
(28, 7, 'trammi pealt', false, false, false),
(29, 8, 'armastad tantsida', true, false, false),
(30, 8, 'armastad tantsima', false, false, false),
(31, 8, 'armastab tantsida', false, false, false),
(32, 8, 'armast tantsimaas', false, false, false),
(33, 9, 'kolm', false, false, false),
(34, 9, 'kolme', false, false, false),
(35, 9, 'kolmekesi', true, false, false),
(36, 9, 'kolmanda', false, false, false),
(37, 10, 'kohvikusse', false, false, false),
(38, 10, 'kodus', true, false, false),
(39, 10, 'koju', false, false, false),
(40, 10, 'kohviku', false, false, false),
(41, 11, 'esimese märts', false, false, false),
(42, 11, 'esimene märtsi', false, false, false),
(43, 11, 'esimese märtsi', false, false, false),
(44, 11, 'esimene märts', true, false, false),
(45, 12, 'käid iga päev tööle', false, false, false),
(46, 12, 'lähed iga päev töölt', false, false, false),
(47, 12, 'lähed iga päev tööle ', true, false, false),
(48, 12, 'lähed iga päev töösse', false, false, false),
(49, 13, 'kasutati', false, false, false),
(50, 13, 'kasutatakse', true, false, false),
(51, 13, 'kasutavad', false, false, false),
(52, 13, 'kasutasid', false, false, false),
(53, 14, 'söödi', false, false, false),
(54, 14, 'söövad', false, false, false),
(55, 14, 'on söödud', true, false, false),
(56, 14, 'söön', false, false, false),
(57, 15, 'juulis', true, false, false),
(58, 15, 'juulist', false, false, false),
(59, 15, 'juulil', false, false, false),
(60, 15, 'juulilt', false, false, false),
(61, 16, 'spordikaupade', false, false, false),
(62, 16, 'mänguasjade', false, false, false),
(63, 16, 'kodumasinate', true, false, false),
(64, 16, 'fotokaupade', false, false, false),
(65, 17, 'Kes', false, false, false),
(66, 17, 'Kas', false, false, false),
(67, 17, 'Kelle', false, false, false),
(68, 17, 'Kus', true, false, false),
(69, 18, 'elas', false, false, false),
(70, 18, 'elavad', false, false, false),
(71, 18, 'elatakse', false, false, false),
(72, 18, 'elasid', true, false, false),
(73, 19, 'tervislik', false, false, false),
(74, 19, 'kasulik', true, false, false),
(75, 19, 'kahjulik', false, false, false),
(76, 19, 'kasukas', false, false, false),
(77, 20, 'ees', true, false, false),
(78, 20, 'ette', false, false, false),
(79, 20, 'eest', false, false, false),
(80, 20, 'eel', false, false, false),
(81, 21, 'peal', false, false, false),
(82, 21, 'edasi', false, false, false),
(83, 21, 'mööda', true, false, false),
(84, 21, 'kaasa', false, false, false),
(85, 22, 'helistada', false, false, false),
(86, 22, 'öelda', false, false, false),
(87, 22, 'ütelda', false, false, false),
(88, 22, 'rääkida', true, false, false),
(89, 23, 'sees', false, false, false),
(90, 23, 'ümber', false, false, false),
(91, 23, 'peal', true, false, false),
(92, 23, 'kohal', false, false, false),
(93, 24, 'pika', false, false, false),
(94, 24, 'pikam', false, false, false),
(95, 24, 'pikem', true, false, false),
(96, 24, 'pikad', false, false, false),
(97, 25, 'tööle', false, false, false),
(98, 25, 'töölt', true, false, false),
(99, 25, 'töös', false, false, false),
(100, 25, 'tööst', false, false, false),
(101, 26, 'võivad juua', false, false, false),
(102, 26, 'võime juua', true, false, false),
(103, 26, 'võime jooma', false, false, false),
(104, 26, 'võivad jooma', false, false, false),
(105, 27, 'toadesse', false, false, false),
(106, 27, 'toasse', false, false, false),
(107, 27, 'tubadesse', true, false, false),
(108, 27, 'toas', false, false, false),
(109, 28, 'jooksed', false, false, false),
(110, 28, 'jookske', false, false, false),
(111, 28, 'jookse', true, false, false),
(112, 28, 'jookseb', false, false, false),
(113, 29, 'Kellel', true, false, false),
(114, 29, 'Kelles', false, false, false),
(115, 29, 'Kellest', false, false, false),
(116, 29, 'Kelleta', false, false, false),
(117, 30, 'Kus', false, false, false),
(118, 30, 'Kuhu', true, false, false),
(119, 30, 'Kust', false, false, false),
(120, 30, 'Mida', false, false, false),
(121, 31, 'majja', true, false, false),
(122, 31, 'majas', false, false, false),
(123, 31, 'majast', false, false, false),
(124, 31, 'majale', false, false, false),
(125, 32, 'rahvuslill', false, false, false),
(126, 32, 'rahvuslind', true, false, false),
(127, 32, 'rahvalaul', false, false, false),
(128, 32, 'rahvalind', false, false, false),
(129, 33, 'uusi voodisid', false, false, false),
(130, 33, 'uusi voodid', false, false, false),
(131, 33, 'uued voodid', false, false, false),
(132, 33, 'uusi voodeid', true, false, false),
(133, 34, 'suurem', true, false, false),
(134, 34, 'suurim', false, false, false),
(135, 34, 'suuram', false, false, false),
(136, 34, 'kõige suurem', false, false, false),
(137, 35, 'joonud', false, false, false),
(138, 35, 'jõin', false, false, false),
(139, 35, 'joodud', true, false, false),
(140, 35, 'juua', false, false, false),
(141, 36, 'lugemata', true, false, false),
(142, 36, 'loevad', false, false, false),
(143, 36, 'loetakse', false, false, false),
(144, 36, 'lugesin', false, false, false),
(145, 37, 'soovitad tellida', false, false, false),
(146, 37, 'soovitate tellida', true, false, false),
(147, 37, 'soovitavad tellida', false, false, false),
(148, 37, 'soovitate tellima', false, false, false),
(149, 38, 'uuega kleidiga', false, false, false),
(150, 38, 'uue kleidiga', true, false, false),
(151, 38, 'uueta kleidita', false, false, false),
(152, 38, 'uued kleidid', false, false, false),
(153, 39, 'lumi', true, false, false),
(154, 39, 'vesi', false, false, false),
(155, 39, 'jõgi', false, false, false),
(156, 39, 'vihm', false, false, false),
(157, 40, 'kingitust', false, false, false),
(158, 40, 'kingituse', true, false, false),
(159, 40, 'kingitus', false, false, false),
(160, 40, 'kingituste', false, false, false),
(161, 41, 'käisid', false, false, false),
(162, 41, 'käivad', false, false, false),
(163, 41, 'käiakse', false, false, false),
(164, 41, 'käidi', true, false, false),
(165, 42, 'minule', false, false, false),
(166, 42, 'mind', true, false, false),
(167, 42, 'minu', false, false, false),
(168, 42, 'mulle', false, false, false),
(169, 43, 'korjama', false, false, false),
(170, 43, 'korjasime', false, false, false),
(171, 43, 'korjamas', true, false, false),
(172, 43, 'korjame', false, false, false),
(173, 44, 'olid läinud', true, false, false),
(174, 44, 'on läinud', false, false, false),
(175, 44, 'läksid', false, false, false),
(176, 44, 'lähevad', false, false, false),
(177, 45, 'sportlast', false, false, false),
(178, 45, 'sportlane', true, false, false),
(179, 45, 'sportlase', false, false, false),
(180, 45, 'sportlasi', false, false, false),
(181, 46, 'graafikut', false, false, false),
(182, 46, 'graafiku', true, false, false),
(183, 46, 'graafikute', false, false, false),
(184, 46, 'graafikuid', false, false, false),
(185, 47, 'müüvad', false, false, false),
(186, 47, 'müüsid', false, false, false),
(187, 47, 'müüakse', true, false, false),
(188, 47, 'müünud', false, false, false),
(189, 48, 'seitsmes', false, false, false),
(190, 48, 'seitsmendas', true, false, false),
(191, 48, 'seitsme', false, false, false),
(192, 48, 'seitset', false, false, false),
(193, 49, 'olid', false, false, false),
(194, 49, 'on', false, false, false),
(195, 49, 'peavad', true, false, false),
(196, 49, 'hoiavad', false, false, false),
(197, 50, 'joon', false, false, false),
(198, 50, 'jõin', false, false, false),
(199, 50, 'joonud', true, false, false),
(200, 50, 'joodud', false, false, false),
(201, 51, 'mul on palav', false, false, false),
(202, 51, 'mul on ükskõik', true, false, false),
(203, 51, 'mul on jahedavõitu', false, false, false),
(204, 51, 'mul on kasukas seljas', false, false, false),
(205, 52, 'juurde', false, false, false),
(206, 52, 'juurest', false, false, false),
(207, 52, 'juures', true, false, false),
(208, 52, 'juurel', false, false, false),
(209, 53, 'ei näe', false, false, false),
(210, 53, 'ei saa rääkida', false, false, false),
(211, 53, 'ei oska laulda', false, false, false),
(212, 53, 'ei kuule', true, false, false),
(213, 54, 'leitud', false, false, false),
(214, 54, 'kukkunud', false, false, false),
(215, 54, 'hukkunud', false, false, false),
(216, 54, 'kadunud', true, false, false),
(217, 55, 'rahulikult', false, false, false),
(218, 55, 'rahulik', true, false, false),
(219, 55, 'rahulikuna', false, false, false),
(220, 55, 'rahulikus', false, false, false),
(221, 56, 'töötanud', true, false, false),
(222, 56, 'töötama', false, false, false),
(223, 56, 'töötada', false, false, false),
(224, 56, 'töötatud', false, false, false),
(225, 57, 'nendel', false, false, false),
(226, 57, 'nendelt ', false, false, false),
(227, 57, 'neid', false, false, false),
(228, 57, 'neile', true, false, false),
(229, 58, 'kirjutamine', false, false, false),
(230, 58, 'kirjutamisest', true, false, false),
(231, 58, 'kirjutada', false, false, false),
(232, 58, 'kirjutanud', false, false, false),
(233, 59, 'sinule', false, false, false),
(234, 59, 'sinu', false, false, false),
(235, 59, 'sinuga', false, false, false),
(236, 59, 'sinust', true, false, false),
(237, 60, 'ette', false, false, false),
(238, 60, 'eest', true, false, false),
(239, 60, 'ees', false, false, false),
(240, 60, 'eel', false, false, false);