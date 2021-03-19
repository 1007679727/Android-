package com.example.chat.msg;

import java.util.List;

public class HistoryMsg {

    /**
     * code : 0
     * data : {"total":33,"totalPage":4,"pageSize":10,"rows":[{"fileName":"20191213132849-67902.wav","msgType":"2","groupId":"269e124679c2430ba29bb59b88cc7371","specifyRecipients":"","content":"1_2019/12/13 下午1:29:41.wav","url":"chat/files/chatFiles/2019-12/2019-12-13/269e124679c2430ba29bb59b88cc7371/1/20191213132849-67902.wav","des":"{\"fileBytes\":221228,\"fileSizeFormate\":\"216KB\",\"duration\":\"2秒\"}","createTime":"2019-12-13 13:28:49","fileSize":0,"custId":"1","contentLength":"2.427","id":"15762149290393451986478","fileType":"wav","fileId":"15762149291093367728749","fileDes":"{\"fileBytes\":221228,\"fileSizeFormate\":\"216KB\",\"duration\":\"2秒\"}"}],"pageNum":1}
     * message : success
     * success : true
     */

    private int code;
    private DataBean data;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * total : 33
         * totalPage : 4
         * pageSize : 10
         * rows : [{"fileName":"20191213132849-67902.wav","msgType":"2","groupId":"269e124679c2430ba29bb59b88cc7371","specifyRecipients":"","content":"1_2019/12/13 下午1:29:41.wav","url":"chat/files/chatFiles/2019-12/2019-12-13/269e124679c2430ba29bb59b88cc7371/1/20191213132849-67902.wav","des":"{\"fileBytes\":221228,\"fileSizeFormate\":\"216KB\",\"duration\":\"2秒\"}","createTime":"2019-12-13 13:28:49","fileSize":0,"custId":"1","contentLength":"2.427","id":"15762149290393451986478","fileType":"wav","fileId":"15762149291093367728749","fileDes":"{\"fileBytes\":221228,\"fileSizeFormate\":\"216KB\",\"duration\":\"2秒\"}"}]
         * pageNum : 1
         */

        private int total;
        private int totalPage;
        private int pageSize;
        private int pageNum;
        private List<Row> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<Row> getRows() {
            return rows;
        }

        public void setRows(List<Row> rows) {
            this.rows = rows;
        }

        public static class Row {
            /**
             * fileName : 20191213132849-67902.wav
             * msgType : 2
             * groupId : 269e124679c2430ba29bb59b88cc7371
             * specifyRecipients :
             * content : 1_2019/12/13 下午1:29:41.wav
             * url : chat/files/chatFiles/2019-12/2019-12-13/269e124679c2430ba29bb59b88cc7371/1/20191213132849-67902.wav
             * des : {"fileBytes":221228,"fileSizeFormate":"216KB","duration":"2秒"}
             * createTime : 2019-12-13 13:28:49
             * fileSize : 0
             * custId : 1
             * contentLength : 2.427
             * id : 15762149290393451986478
             * fileType : wav
             * fileId : 15762149291093367728749
             * fileDes : {"fileBytes":221228,"fileSizeFormate":"216KB","duration":"2秒"}
             */

            private String fileName;
            private String msgType;
            private String groupId;
            private String specifyRecipients;
            private String content;
            private String url;
            private String des;
            private String createTime;
            private int fileSize;
            private String custId;
            private String contentLength;
            private String id;
            private String fileType;
            private String fileUrl;
            private String fileId;
            private String fileDes;
            private String isDel;
            private String custAliasName;
            private String custAliasId;
            private String unreadCustIds;
            private String custReadMsgInfo;

            public String getUnreadCustIds() {
                return unreadCustIds;
            }

            public void setUnreadCustIds(String unreadCustIds) {
                this.unreadCustIds = unreadCustIds;
            }

            public String getCustReadMsgInfo() {
                return custReadMsgInfo;
            }

            public void setCustReadMsgInfo(String custReadMsgInfo) {
                this.custReadMsgInfo = custReadMsgInfo;
            }

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getMsgType() {
                return msgType;
            }

            public void setMsgType(String msgType) {
                this.msgType = msgType;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getSpecifyRecipients() {
                return specifyRecipients;
            }

            public void setSpecifyRecipients(String specifyRecipients) {
                this.specifyRecipients = specifyRecipients;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getFileSize() {
                return fileSize;
            }

            public void setFileSize(int fileSize) {
                this.fileSize = fileSize;
            }

            public String getCustId() {
                return custId;
            }

            public void setCustId(String custId) {
                this.custId = custId;
            }

            public String getContentLength() {
                return contentLength;
            }

            public void setContentLength(String contentLength) {
                this.contentLength = contentLength;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFileType() {
                return fileType;
            }

            public void setFileType(String fileType) {
                this.fileType = fileType;
            }

            public String getFileId() {
                return fileId;
            }

            public void setFileId(String fileId) {
                this.fileId = fileId;
            }

            public String getFileDes() {
                return fileDes;
            }

            public void setFileDes(String fileDes) {
                this.fileDes = fileDes;
            }

            public String getIsDel() {
                return isDel;
            }

            public void setIsDel(String isDel) {
                this.isDel = isDel;
            }

            public String getCustAliasName() {
                return custAliasName;
            }

            public void setCustAliasName(String custAliasName) {
                this.custAliasName = custAliasName;
            }

            public String getCustAliasId() {
                return custAliasId;
            }

            public void setCustAliasId(String custAliasId) {
                this.custAliasId = custAliasId;
            }

            @Override
            public String toString() {
                return "Row{" +
                        "fileName='" + fileName + '\'' +
                        ", msgType='" + msgType + '\'' +
                        ", groupId='" + groupId + '\'' +
                        ", specifyRecipients='" + specifyRecipients + '\'' +
                        ", content='" + content + '\'' +
                        ", url='" + url + '\'' +
                        ", des='" + des + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", fileSize=" + fileSize +
                        ", custId='" + custId + '\'' +
                        ", contentLength='" + contentLength + '\'' +
                        ", id='" + id + '\'' +
                        ", fileType='" + fileType + '\'' +
                        ", fileUrl='" + fileUrl + '\'' +
                        ", fileId='" + fileId + '\'' +
                        ", fileDes='" + fileDes + '\'' +
                        ", isDel='" + isDel + '\'' +
                        ", custAliasName='" + custAliasName + '\'' +
                        ", custAliasId='" + custAliasId + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "total=" + total +
                    ", totalPage=" + totalPage +
                    ", pageSize=" + pageSize +
                    ", pageNum=" + pageNum +
                    ", rows=" + rows +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HistoryMsg{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
