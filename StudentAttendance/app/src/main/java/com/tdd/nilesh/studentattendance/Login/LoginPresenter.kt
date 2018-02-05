package com.tdd.nilesh.studentattendance.Login

/**
 * Created by rlindoso on 16/11/2017.
 */
class LoginPresenter {
    private val MAX_TENTATIVA_LOGIN = 3
    private var loginTentativas = 0

    fun incrementaTentativaLogin(): Int {
        loginTentativas += 1
        return loginTentativas
    }
    fun isTentativaLoginExcedida(): Boolean {
        return loginTentativas >= MAX_TENTATIVA_LOGIN
    }

    fun isLoginSucesso(usuario: String, senha: String): Boolean {
        if (isTentativaLoginExcedida()){
            return false
        }
        if (usuario == "abc" && senha == "123") {
            return true
        }
        incrementaTentativaLogin()
        return false
    }
}