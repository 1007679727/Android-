package com.example.chat.msg;

public class UploadMsgFileRes {
    /**
     * code : 0
     * data : {"chatFile":{"chatId":"15767411452828177031040","des":"","fileSize":0,"filename":"20191219153905-67884.jpg","filetype":"jpg","id":"15767411453663957117403","url":"chat/files/chatFiles/2019-12/2019-12-19/27ea62474a9f4f9ba60e243f109334da/1/20191219153905-67884.jpg"},"chatRecord":{"content":"IMG_20190704_110757.jpg","contentLength":"","createTime":"2019-12-19 15:39:05","custId":"1","des":"","groupId":"27ea62474a9f4f9ba60e243f109334da","id":"15767411452828177031040","isDel":"","msgType":"5","specifyRecipients":""}}
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
         * chatFile : {"chatId":"15767411452828177031040","des":"","fileSize":0,"filename":"20191219153905-67884.jpg","filetype":"jpg","id":"15767411453663957117403","url":"chat/files/chatFiles/2019-12/2019-12-19/27ea62474a9f4f9ba60e243f109334da/1/20191219153905-67884.jpg"}
         * chatRecord : {"content":"IMG_20190704_110757.jpg","contentLength":"","createTime":"2019-12-19 15:39:05","custId":"1","des":"","groupId":"27ea62474a9f4f9ba60e243f109334da","id":"15767411452828177031040","isDel":"","msgType":"5","specifyRecipients":""}
         */

        private ChatFileBean chatFile;
        private ChatRecordBean chatRecord;

        public ChatFileBean getChatFile() {
            return chatFile;
        }

        public void setChatFile(ChatFileBean chatFile) {
            this.chatFile = chatFile;
        }

        public ChatRecordBean getChatRecord() {
            return chatRecord;
        }

        public void setChatRecord(ChatRecordBean chatRecord) {
            this.chatRecord = chatRecord;
        }

        public static class ChatFileBean {
            /**
             * chatId : 15767411452828177031040
             * des :
             * fileSize : 0
             * filename : 20191219153905-67884.jpg
             * filetype : jpg
             * id : 15767411453663957117403
             * url : chat/files/chatFiles/2019-12/2019-12-19/27ea62474a9f4f9ba60e243f109334da/1/20191219153905-67884.jpg
             */

            private String chatId;
            private String des;
            private int fileSize;
            private String filename;
            private String filetype;
            private String id;
            private String url;

            public String getChatId() {
                return chatId;
            }

            public void setChatId(String chatId) {
                this.chatId = chatId;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public int getFileSize() {
                return fileSize;
            }

            public void setFileSize(int fileSize) {
                this.fileSize = fileSize;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getFiletype() {
                return filetype;
            }

            public void setFiletype(String filetype) {
                this.filetype = filetype;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ChatRecordBean {
            /**
             * content : IMG_20190704_110757.jpg
             * contentLength :
             * createTime : 2019-12-19 15:39:05
             * custId : 1
             * des :
             * groupId : 27ea62474a9f4f9ba60e243f109334da
             * id : 15767411452828177031040
             * isDel :
             * msgType : 5
             * specifyRecipients :
             */

            private String content;
            private String contentLength;
            private String createTime;
            private String custId;
            private String des;
            private String groupId;
            private String id;
            private String isDel;
            private String msgType;
            private String specifyRecipients;
            private String unreadCustIds;
            private String custReadMsgInfo;
            private String custAliasName;
            private String custAliasId;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getContentLength() {
                return contentLength;
            }

            public void setContentLength(String contentLength) {
                this.contentLength = contentLength;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCustId() {
                return custId;
            }

            public void setCustId(String custId) {
                this.custId = custId;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsDel() {
                return isDel;
            }

            public void setIsDel(String isDel) {
                this.isDel = isDel;
            }

            public String getMsgType() {
                return msgType;
            }

            public void setMsgType(String msgType) {
                this.msgType = msgType;
            }

            public String getSpecifyRecipients() {
                return specifyRecipients;
            }

            public void setSpecifyRecipients(String specifyRecipients) {
                this.specifyRecipients = specifyRecipients;
            }
        }
    }
}
