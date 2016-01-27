package com.chs.organizationlevel.bean;

import java.util.List;

/**
 * 作者：chs on 2015/12/31 11:50
 * 邮箱：657083984@qq.com
 */
public class MyEntity {


    private int returncode;

    private List<DataEntity> data;

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getReturncode() {
        return returncode;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String id;
        private String sort;
        private String pid;
        private String ppath;
        private String name;
        private String is_show;
        private String del_user;
        private String del_time;
        private String del_state;
        private boolean isExpand = false;

        public boolean isExpand() {
            return isExpand;
        }

        public void setIsExpand(boolean isExpand) {
            this.isExpand = isExpand;
        }

        private List<UserListEntity> user_list;
        private List<DataEntity> list;

        public void setId(String id) {
            this.id = id;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setPpath(String ppath) {
            this.ppath = ppath;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public void setDel_user(String del_user) {
            this.del_user = del_user;
        }

        public void setDel_time(String del_time) {
            this.del_time = del_time;
        }

        public void setDel_state(String del_state) {
            this.del_state = del_state;
        }

        public void setUser_list(List<UserListEntity> user_list) {
            this.user_list = user_list;
        }

        public void setList(List<DataEntity> list) {
            this.list = list;
        }

        public String getId() {
            return id;
        }

        public String getSort() {
            return sort;
        }

        public String getPid() {
            return pid;
        }

        public String getPpath() {
            return ppath;
        }

        public String getName() {
            return name;
        }

        public String getIs_show() {
            return is_show;
        }

        public String getDel_user() {
            return del_user;
        }

        public String getDel_time() {
            return del_time;
        }

        public String getDel_state() {
            return del_state;
        }

        public List<UserListEntity> getUser_list() {
            return user_list;
        }

        public List<DataEntity> getList() {
            return list;
        }

        public static class UserListEntity {
            private String id;
            private String user_id;
            private String shops_id;
            private String out_userid;
            private String directory_id;
            private String name;
            private String role_name;
            private String role_id;
            private String head;
            private Object email;
            private String subgroup;
            private Object subgroup_name;
            private String gender;
            private String mobile;
            private String del_state;
            private String del_user;
            private String del_time;

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setShops_id(String shops_id) {
                this.shops_id = shops_id;
            }

            public void setOut_userid(String out_userid) {
                this.out_userid = out_userid;
            }

            public void setDirectory_id(String directory_id) {
                this.directory_id = directory_id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setRole_name(String role_name) {
                this.role_name = role_name;
            }

            public void setRole_id(String role_id) {
                this.role_id = role_id;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public void setSubgroup(String subgroup) {
                this.subgroup = subgroup;
            }

            public void setSubgroup_name(Object subgroup_name) {
                this.subgroup_name = subgroup_name;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setDel_state(String del_state) {
                this.del_state = del_state;
            }

            public void setDel_user(String del_user) {
                this.del_user = del_user;
            }

            public void setDel_time(String del_time) {
                this.del_time = del_time;
            }

            public String getId() {
                return id;
            }

            public String getShops_id() {
                return shops_id;
            }

            public String getOut_userid() {
                return out_userid;
            }

            public String getDirectory_id() {
                return directory_id;
            }

            public String getName() {
                return name;
            }

            public String getRole_name() {
                return role_name;
            }

            public String getRole_id() {
                return role_id;
            }

            public String getHead() {
                return head;
            }

            public Object getEmail() {
                return email;
            }

            public String getSubgroup() {
                return subgroup;
            }

            public Object getSubgroup_name() {
                return subgroup_name;
            }

            public String getGender() {
                return gender;
            }

            public String getMobile() {
                return mobile;
            }

            public String getDel_state() {
                return del_state;
            }

            public String getDel_user() {
                return del_user;
            }

            public String getDel_time() {
                return del_time;
            }
        }
    }
}
