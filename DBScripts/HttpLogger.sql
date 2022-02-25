

CREATE TABLE HttpLogger (
  logId varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
  requestBusinessCategory varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  requestTransactionId varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  requestIp varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  requestUrl varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  requestUserAgent varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  authorization varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  proxyAuthorization varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  httpMethod varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  contentType varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  encoding varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  referer varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  createTime datetime  NULL,
  updateTime datetime  NULL,
  requestParams varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  requestBody varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  responseStatus varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  responseBody varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL,
  processTime bigint  NULL,
	PRIMARY KEY (`logId`) USING BTREE
)ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Http日志' ROW_FORMAT = Dynamic;







