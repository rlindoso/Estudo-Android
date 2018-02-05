package com.lindoso.rafael.mynotesapp.notas


/**
 * Created by rlindoso on 17/11/2017.
 */
class NotasPresenter(mView: NotasMVP.RequiredViewOps?): NotasMVP.RequiredPresenterOps, NotasMVP.PresenterOps {
    // Referência para layer View
    private var mView: NotasMVP.RequiredViewOps? = null
    // Referência para o layer Model
    private lateinit var mModel: NotasMVP.ModelOps

    // Estado da mudança de configuração
    private var mIsChangingConfig: Boolean = false

    override fun onConfigurationChanged(view: NotasMVP.RequiredViewOps) {
        mView = view
    }

    override fun onDestroy(isChangingConfig: Boolean) {
        mView = null
        mIsChangingConfig = isChangingConfig
        if ( !isChangingConfig ) {
            mModel.onDestroy()
        }
    }

    override fun novaNota(textoNota: String) {
        val nota = Nota()
        nota.textoNota = textoNota
        nota.date = getDate()
        mModel.insereNota(nota)
    }

    override fun deletaNota(nota: Nota) {
        mModel.removeNota(nota)
    }

    override fun onNotaInserida(novaNota: Nota) {
        mView!!.showToast("Novo registro " + novaNota.date)
    }

    override fun onNotaRemovida(notaRemovida: Nota) {
        mView!!.showToast("Nota de " + notaRemovida.date + " removida")
    }

    override fun onError(errorMsg: String) {
        mView!!.showAlert(errorMsg)
    }

    /**
     * Retorna data atual
     */
    private fun getDate(): String {
        return "hoje"
    }
}