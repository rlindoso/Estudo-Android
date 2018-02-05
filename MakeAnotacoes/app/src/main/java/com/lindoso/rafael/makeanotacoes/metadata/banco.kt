import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by allanromanato on 5/27/15.
 */
class CriaBanco(context: Context) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = ("CREATE TABLE" + TABELA + "("
                + ID + "integer primary key autoincrement,"
                + TITULO + "text,"
                + AUTOR + "text,"
                + TEXTO + "text"
                + ")")
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA)
        onCreate(db)
    }

    companion object {
        private val NOME_BANCO = "banco.db"
        private val TABELA = "anotacoes"
        private val ID = "_id"
        private val TITULO = "titulo"
        private val AUTOR = "autor"
        private val TEXTO = "texto"
        private val VERSAO = 1
    }
}