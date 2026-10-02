package com.impulsa.app.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VentaDao_Impl implements VentaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VentaEntity> __insertionAdapterOfVentaEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarcarSincronizada;

  public VentaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVentaEntity = new EntityInsertionAdapter<VentaEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `ventas` (`id`,`cliente`,`concepto`,`valor`,`formaPago`,`fecha`,`nota`,`sincronizada`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VentaEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getCliente() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCliente());
        }
        if (entity.getConcepto() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getConcepto());
        }
        statement.bindDouble(4, entity.getValor());
        if (entity.getFormaPago() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFormaPago());
        }
        statement.bindLong(6, entity.getFecha());
        if (entity.getNota() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNota());
        }
        final int _tmp = entity.getSincronizada() ? 1 : 0;
        statement.bindLong(8, _tmp);
      }
    };
    this.__preparedStmtOfMarcarSincronizada = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE ventas SET sincronizada = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertar(final VentaEntity venta, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfVentaEntity.insertAndReturnId(venta);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object marcarSincronizada(final long ventaId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarcarSincronizada.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, ventaId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarcarSincronizada.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VentaEntity>> obtenerTodas() {
    final String _sql = "SELECT * FROM ventas ORDER BY fecha DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ventas"}, new Callable<List<VentaEntity>>() {
      @Override
      @NonNull
      public List<VentaEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCliente = CursorUtil.getColumnIndexOrThrow(_cursor, "cliente");
          final int _cursorIndexOfConcepto = CursorUtil.getColumnIndexOrThrow(_cursor, "concepto");
          final int _cursorIndexOfValor = CursorUtil.getColumnIndexOrThrow(_cursor, "valor");
          final int _cursorIndexOfFormaPago = CursorUtil.getColumnIndexOrThrow(_cursor, "formaPago");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfNota = CursorUtil.getColumnIndexOrThrow(_cursor, "nota");
          final int _cursorIndexOfSincronizada = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizada");
          final List<VentaEntity> _result = new ArrayList<VentaEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VentaEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCliente;
            if (_cursor.isNull(_cursorIndexOfCliente)) {
              _tmpCliente = null;
            } else {
              _tmpCliente = _cursor.getString(_cursorIndexOfCliente);
            }
            final String _tmpConcepto;
            if (_cursor.isNull(_cursorIndexOfConcepto)) {
              _tmpConcepto = null;
            } else {
              _tmpConcepto = _cursor.getString(_cursorIndexOfConcepto);
            }
            final double _tmpValor;
            _tmpValor = _cursor.getDouble(_cursorIndexOfValor);
            final String _tmpFormaPago;
            if (_cursor.isNull(_cursorIndexOfFormaPago)) {
              _tmpFormaPago = null;
            } else {
              _tmpFormaPago = _cursor.getString(_cursorIndexOfFormaPago);
            }
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            final String _tmpNota;
            if (_cursor.isNull(_cursorIndexOfNota)) {
              _tmpNota = null;
            } else {
              _tmpNota = _cursor.getString(_cursorIndexOfNota);
            }
            final boolean _tmpSincronizada;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizada);
            _tmpSincronizada = _tmp != 0;
            _item = new VentaEntity(_tmpId,_tmpCliente,_tmpConcepto,_tmpValor,_tmpFormaPago,_tmpFecha,_tmpNota,_tmpSincronizada);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object obtenerPorId(final long ventaId,
      final Continuation<? super VentaEntity> $completion) {
    final String _sql = "SELECT * FROM ventas WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, ventaId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VentaEntity>() {
      @Override
      @Nullable
      public VentaEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCliente = CursorUtil.getColumnIndexOrThrow(_cursor, "cliente");
          final int _cursorIndexOfConcepto = CursorUtil.getColumnIndexOrThrow(_cursor, "concepto");
          final int _cursorIndexOfValor = CursorUtil.getColumnIndexOrThrow(_cursor, "valor");
          final int _cursorIndexOfFormaPago = CursorUtil.getColumnIndexOrThrow(_cursor, "formaPago");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfNota = CursorUtil.getColumnIndexOrThrow(_cursor, "nota");
          final int _cursorIndexOfSincronizada = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizada");
          final VentaEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCliente;
            if (_cursor.isNull(_cursorIndexOfCliente)) {
              _tmpCliente = null;
            } else {
              _tmpCliente = _cursor.getString(_cursorIndexOfCliente);
            }
            final String _tmpConcepto;
            if (_cursor.isNull(_cursorIndexOfConcepto)) {
              _tmpConcepto = null;
            } else {
              _tmpConcepto = _cursor.getString(_cursorIndexOfConcepto);
            }
            final double _tmpValor;
            _tmpValor = _cursor.getDouble(_cursorIndexOfValor);
            final String _tmpFormaPago;
            if (_cursor.isNull(_cursorIndexOfFormaPago)) {
              _tmpFormaPago = null;
            } else {
              _tmpFormaPago = _cursor.getString(_cursorIndexOfFormaPago);
            }
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            final String _tmpNota;
            if (_cursor.isNull(_cursorIndexOfNota)) {
              _tmpNota = null;
            } else {
              _tmpNota = _cursor.getString(_cursorIndexOfNota);
            }
            final boolean _tmpSincronizada;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizada);
            _tmpSincronizada = _tmp != 0;
            _result = new VentaEntity(_tmpId,_tmpCliente,_tmpConcepto,_tmpValor,_tmpFormaPago,_tmpFecha,_tmpNota,_tmpSincronizada);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
