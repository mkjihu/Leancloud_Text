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
 * DAO for table "DIALOGUE".
*/
public class DialogueDao extends AbstractDao<Dialogue, Long> {

    public static final String TABLENAME = "DIALOGUE";

    /**
     * Properties of entity Dialogue.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Conversation_id = new Property(1, String.class, "conversation_id", false, "CONVERSATION_ID");
        public final static Property Conversation_name = new Property(2, String.class, "conversation_name", false, "CONVERSATION_NAME");
        public final static Property From_ID = new Property(3, String.class, "From_ID", false, "FROM__ID");
        public final static Property Content = new Property(4, String.class, "content", false, "CONTENT");
        public final static Property Upadte_time = new Property(5, java.util.Date.class, "upadte_time", false, "UPADTE_TIME");
        public final static Property IsRead = new Property(6, Boolean.class, "isRead", false, "IS_READ");
    }


    public DialogueDao(DaoConfig config) {
        super(config);
    }
    
    public DialogueDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DIALOGUE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CONVERSATION_ID\" TEXT," + // 1: conversation_id
                "\"CONVERSATION_NAME\" TEXT," + // 2: conversation_name
                "\"FROM__ID\" TEXT," + // 3: From_ID
                "\"CONTENT\" TEXT," + // 4: content
                "\"UPADTE_TIME\" INTEGER," + // 5: upadte_time
                "\"IS_READ\" INTEGER);"); // 6: isRead
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DIALOGUE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Dialogue entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String conversation_id = entity.getConversation_id();
        if (conversation_id != null) {
            stmt.bindString(2, conversation_id);
        }
 
        String conversation_name = entity.getConversation_name();
        if (conversation_name != null) {
            stmt.bindString(3, conversation_name);
        }
 
        String From_ID = entity.getFrom_ID();
        if (From_ID != null) {
            stmt.bindString(4, From_ID);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        java.util.Date upadte_time = entity.getUpadte_time();
        if (upadte_time != null) {
            stmt.bindLong(6, upadte_time.getTime());
        }
 
        Boolean isRead = entity.getIsRead();
        if (isRead != null) {
            stmt.bindLong(7, isRead ? 1L: 0L);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Dialogue entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String conversation_id = entity.getConversation_id();
        if (conversation_id != null) {
            stmt.bindString(2, conversation_id);
        }
 
        String conversation_name = entity.getConversation_name();
        if (conversation_name != null) {
            stmt.bindString(3, conversation_name);
        }
 
        String From_ID = entity.getFrom_ID();
        if (From_ID != null) {
            stmt.bindString(4, From_ID);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        java.util.Date upadte_time = entity.getUpadte_time();
        if (upadte_time != null) {
            stmt.bindLong(6, upadte_time.getTime());
        }
 
        Boolean isRead = entity.getIsRead();
        if (isRead != null) {
            stmt.bindLong(7, isRead ? 1L: 0L);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Dialogue readEntity(Cursor cursor, int offset) {
        Dialogue entity = new Dialogue( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // conversation_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // conversation_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // From_ID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // content
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // upadte_time
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0 // isRead
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Dialogue entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setConversation_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setConversation_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFrom_ID(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setContent(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUpadte_time(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setIsRead(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Dialogue entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Dialogue entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Dialogue entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
