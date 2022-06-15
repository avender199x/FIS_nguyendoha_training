-- MySQL Dump

--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 13, 2022 lúc 10:01 PM


SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `nguyendoha_fis`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `criminal_case`
--

CREATE TABLE `criminal_case`
(
    `id`                    bigint(20) NOT NULL,
    `created_at`            datetime     NOT NULL,
    `detail_description`    varchar(255) NOT NULL,
    `modified_at`           datetime     NOT NULL,
    `notes`                 varchar(255) NOT NULL,
    `number`                varchar(255) NOT NULL,
    `short_description`     varchar(255) NOT NULL,
    `status`                varchar(255) NOT NULL,
    `type`                  varchar(255) NOT NULL,
    `lead_investigator_one` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `detective`
--

CREATE TABLE `detective`
(
    `id`           bigint(20) NOT NULL,
    `armed`        bit(1)       NOT NULL,
    `badgetnumber` varchar(255) NOT NULL,
    `created_at`   datetime     NOT NULL,
    `modified_at`  datetime     NOT NULL,
    `rank`         varchar(255) NOT NULL,
    `status`       varchar(255) NOT NULL,
    `person_one`   bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `evidence`
--

CREATE TABLE `evidence`
(
    `id`                bigint(20) NOT NULL,
    `archived`          bit(1)       NOT NULL,
    `created_at`        datetime     NOT NULL,
    `item_name`         varchar(255) NOT NULL,
    `modified_at`       datetime     NOT NULL,
    `notes`             varchar(255) NOT NULL,
    `number`            varchar(255) NOT NULL,
    `criminal_case_one` bigint(20) DEFAULT NULL,
    `storage_one`       bigint(20) DEFAULT NULL,
    `evidence_set`      bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `person`
--

CREATE TABLE `person`
(
    `id`          bigint(20) NOT NULL,
    `created_at`  datetime     NOT NULL,
    `first_name`  varchar(255) NOT NULL,
    `hiring_date` datetime     NOT NULL,
    `last_name`   varchar(255) NOT NULL,
    `modified_at` datetime     NOT NULL,
    `password`    varchar(255) NOT NULL,
    `user_name`   varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `storage`
--

CREATE TABLE `storage`
(
    `id`          bigint(20) NOT NULL,
    `created_at`  datetime     NOT NULL,
    `location`    varchar(255) NOT NULL,
    `modified_at` datetime     NOT NULL,
    `name`        varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `track_entry`
--

CREATE TABLE `track_entry`
(
    `id`            bigint(20) NOT NULL,
    `action`        varchar(255) NOT NULL,
    `created_at`    datetime     NOT NULL,
    `date`          datetime     NOT NULL,
    `modified_at`   datetime     NOT NULL,
    `resson`        varchar(255) NOT NULL,
    `detective_one` bigint(20) DEFAULT NULL,
    `evidence_one`  bigint(20) DEFAULT NULL,
    `track_entries` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `working_detective_case`
--

CREATE TABLE `working_detective_case`
(
    `criminal_case_id` bigint(20) NOT NULL,
    `detective_id`     bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `criminal_case`
--
ALTER TABLE `criminal_case`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKnxg0nv9964g3kc3hqj3a22dmu` (`lead_investigator_one`);

--
-- Chỉ mục cho bảng `detective`
--
ALTER TABLE `detective`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK9v3y2brpco2mn7sjyh7xjt6co` (`person_one`);

--
-- Chỉ mục cho bảng `evidence`
--
ALTER TABLE `evidence`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKsossnv4p9hbf7tw7fr1ye25lp` (`criminal_case_one`),
  ADD KEY `FK4dcpbwrakn9hlak1c6xhd27fn` (`storage_one`),
  ADD KEY `FKgr5ou7ynj4e2lb8j8dche9ep4` (`evidence_set`);

--
-- Chỉ mục cho bảng `person`
--
ALTER TABLE `person`
    ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `storage`
--
ALTER TABLE `storage`
    ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `track_entry`
--
ALTER TABLE `track_entry`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKlb9gfivasb21qkqfy3imoupuc` (`detective_one`),
  ADD KEY `FKarmq6emhaej2u0he7f6l0mb48` (`evidence_one`),
  ADD KEY `FKrxf7i20t61gon4dx4cqmr7upf` (`track_entries`);

--
-- Chỉ mục cho bảng `working_detective_case`
--
ALTER TABLE `working_detective_case`
    ADD PRIMARY KEY (`criminal_case_id`, `detective_id`),
  ADD KEY `FKa1s54tvdsgnww8w1nk0d4grw7` (`detective_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `criminal_case`
--
ALTER TABLE `criminal_case`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `detective`
--
ALTER TABLE `detective`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `evidence`
--
ALTER TABLE `evidence`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `person`
--
ALTER TABLE `person`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `storage`
--
ALTER TABLE `storage`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `track_entry`
--
ALTER TABLE `track_entry`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `criminal_case`
--
ALTER TABLE `criminal_case`
    ADD CONSTRAINT `FKnxg0nv9964g3kc3hqj3a22dmu` FOREIGN KEY (`lead_investigator_one`) REFERENCES `detective` (`id`);

--
-- Các ràng buộc cho bảng `detective`
--
ALTER TABLE `detective`
    ADD CONSTRAINT `FK9v3y2brpco2mn7sjyh7xjt6co` FOREIGN KEY (`person_one`) REFERENCES `person` (`id`);

--
-- Các ràng buộc cho bảng `evidence`
--
ALTER TABLE `evidence`
    ADD CONSTRAINT `FK4dcpbwrakn9hlak1c6xhd27fn` FOREIGN KEY (`storage_one`) REFERENCES `storage` (`id`),
  ADD CONSTRAINT `FKgr5ou7ynj4e2lb8j8dche9ep4` FOREIGN KEY (`evidence_set`) REFERENCES `criminal_case` (`id`),
  ADD CONSTRAINT `FKsossnv4p9hbf7tw7fr1ye25lp` FOREIGN KEY (`criminal_case_one`) REFERENCES `criminal_case` (`id`);

--
-- Các ràng buộc cho bảng `track_entry`
--
ALTER TABLE `track_entry`
    ADD CONSTRAINT `FKarmq6emhaej2u0he7f6l0mb48` FOREIGN KEY (`evidence_one`) REFERENCES `evidence` (`id`),
  ADD CONSTRAINT `FKlb9gfivasb21qkqfy3imoupuc` FOREIGN KEY (`detective_one`) REFERENCES `detective` (`id`),
  ADD CONSTRAINT `FKrxf7i20t61gon4dx4cqmr7upf` FOREIGN KEY (`track_entries`) REFERENCES `evidence` (`id`);

--
-- Các ràng buộc cho bảng `working_detective_case`
--
ALTER TABLE `working_detective_case`
    ADD CONSTRAINT `FKa1s54tvdsgnww8w1nk0d4grw7` FOREIGN KEY (`detective_id`) REFERENCES `detective` (`id`),
  ADD CONSTRAINT `FKdil1783758qven9tcvyrroyhi` FOREIGN KEY (`criminal_case_id`) REFERENCES `criminal_case` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
