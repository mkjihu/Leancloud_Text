package com.leancloud_text.SQL;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "UNREAD_MESSAGE".
*/
public class UnreadMessageDao extends AbstractDao<UnreadMessage, Long> {

    public static final String TABLENAME = "UNREAD_MESSAGE";

    /**
     * Properties of entity UnreadMessage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Conversation_id = new Property(1, String.class, "conversation_id", false, "CONVERSATION_ID");
        public final static Property Members = new Property(2, String.class, "members", false, "MEMBERS");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property UnreadCount = new Property(4, String.class, "unreadCount", false, "UNREAD_COUNT");
        public final static Property Content = new Property(5, String.class, "content", false, "CONTENT");
        public final static Property Upadte_time = new Property(6, java.util.Date.class, "upadte_time", false, "UPADTE_TIME");
    }


    public UnreadMessageDao(DaoConfig config) {
        super(config);
    }
    
    public UnreadMessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"UNREAD_MESSAGE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CONVERSATION_ID\" TEXT NOT NULL UNIQUE ," + // 1: conversation_id
                "\"MEMBERS\" TEXT," + // 2: members
                "\"NAME\" TEXT NOT NULL ," + // 3: name
                "\"UNREAD_COUNT\" TEXT," + // 4: unreadCount
                "\"CONTENT\" TEXT," + // 5: content
                "\"UPADTE_TIME\" INTEGER);"); // 6: upadte_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"UNREAD_MESSAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UnreadMessage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getConversation_id());
 
        String members = entity.getMembers();
        if (members != null) {
            stmt.bindString(3, members);
        }
        stmt.bindString(4, entity.getName());
 
        String unreadCount = entity.getUnreadCount();
        if (unreadCount != null) {
            stmt.bindString(5, unreadCount);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(6, content);
        }
 
        java.util.Date upadte_time = entity.getUpadte_time();
        if (upadte_time != null) {
            stmt.bindLong(7, upadte_time.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UnreadMessage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getConversation_id());
 
        String members = entity.getMembers();
        if (members != null) {
            stmt.bindString(3, members);
        }
        stmt.bindString(4, entity.getName());
 
        String unreadCount = entity.getUnreadCount();
        if (unreadCount != null) {
            stmt.bindString(5, unreadCount);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(6, content);
        }
 
        java.util.Date upadte_time = entity.getUpadte_time();
        if (upadte_time != null) {
            stmt.bindLong(7, upadte_time.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UnreadMessage readEntity(Cursor cursor, int offset) {
        UnreadMessage entity = new UnreadMessage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // conversation_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // members
            cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // unreadCount
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // content
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)) // upadte_time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UnreadMessage entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setConversation_id(cursor.getString(offset + 1));
        entity.setMembers(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.getString(offset + 3));
        entity.setUnreadCount(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setContent(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUpadte_time(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UnreadMessage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UnreadMessage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UnreadMessage entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
