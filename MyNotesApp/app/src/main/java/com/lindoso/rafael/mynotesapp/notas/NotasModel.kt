package com.lindoso.rafael.mynotesapp.notas

/**
 * Created by rlindoso on 17/11/2017.
 */
class NotasModel: NotasMVP.ModelOps {
    override fun insereNota(nota: Nota) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeNota(nota: Nota) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class Nota {
    var textoNota: String = ""
    var date: String = ""
}
