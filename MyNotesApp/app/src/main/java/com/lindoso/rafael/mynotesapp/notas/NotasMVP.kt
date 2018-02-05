package com.lindoso.rafael.mynotesapp.notas

/**
 * Created by rlindoso on 17/11/2017.
 * Interface guarda-chuva do padrão MVP, agrega todas as operações de
 * comunicação entre os diferentes layer do padrão: MODEL, VIEW, PRESENTER
 */

interface NotasMVP {

    /**
     * Métodos obrigatórios em View, disponíveis para Presenter
     * Presenter -> View
     */
    interface RequiredViewOps {
        fun showToast(msg: String)
        fun showAlert(msg: String)
        // qualquer outra operação na UI
    }

    /**
     * operações oferecidas ao layer View para comunicação com Presenter
     * View -> Presenter
     */
    interface PresenterOps {
        fun onConfigurationChanged(view: RequiredViewOps)
        fun onDestroy(isChangingConfig: Boolean)
        fun novaNota(textoNota: String)
        fun deletaNota(nota: Nota)
        // qualquer outra operação a ser chamada pelo View
    }

    /**
     * operações oferecidas pelo layer Presenter para comunicações com Model
     * Model -> Presenter
     */
    interface RequiredPresenterOps {
        fun onNotaInserida(novaNota: Nota)
        fun onNotaRemovida(notaRemovida: Nota)
        fun onError(errorMsg: String)
        // qualquer operação de retorno Model -> Presenter
    }

    /**
     * operações oferecidos pelo layer Model para comunicações com Presenter
     * Presenter -> Model
     */
    interface ModelOps {
        fun insereNota(nota: Nota)
        fun removeNota(nota: Nota)
        fun onDestroy()
        // Qualquer operação referente à dados a ser chamado pelo Presenter
    }
}
