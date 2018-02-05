package com.tdd.nilesh.studentattendance.Login

import org.junit.Assert
import org.junit.Test

/**
 * Created by rlindoso on 16/11/2017.
 */
class LoginPresenterTest {
    @Test
    fun verificaSeTentativasDeLoginExcederam(){
        val loginPresenter = LoginPresenter()
        Assert.assertEquals(1, loginPresenter.incrementaTentativaLogin())
        Assert.assertEquals(2, loginPresenter.incrementaTentativaLogin())
        Assert.assertEquals(3, loginPresenter.incrementaTentativaLogin())
        Assert.assertTrue(loginPresenter.isTentativaLoginExcedida())
    }

    @Test
    fun verificaSeTentativasDeLoginNaoExcederam() {
        val loginPresenter = LoginPresenter()
        Assert.assertEquals(1, loginPresenter.incrementaTentativaLogin())
        Assert.assertFalse(loginPresenter.isTentativaLoginExcedida())
        Assert.assertEquals(2, loginPresenter.incrementaTentativaLogin())
        Assert.assertFalse(loginPresenter.isTentativaLoginExcedida())
    }

    @Test
    fun verificaSeUsuarioESenhaEstaoCorretos(){
        val loginPresenter = LoginPresenter()
        Assert.assertTrue(loginPresenter.isLoginSucesso("abc", "123"))
    }

    @Test
    fun verificaSeUsuarioESenhaEstaoIncorretos(){
        val loginPresenter = LoginPresenter()
        Assert.assertFalse(loginPresenter.isLoginSucesso("abcd", "1234"))
    }

    @Test
    fun verificaSeUsuarioESenhaExcedendoTentativas(){
        val loginPresenter = LoginPresenter()
        Assert.assertFalse(loginPresenter.isLoginSucesso("abcd", "1234"))
        Assert.assertFalse(loginPresenter.isLoginSucesso("abcd", "1234"))
        Assert.assertFalse(loginPresenter.isLoginSucesso("abcd", "1234"))
        Assert.assertFalse(loginPresenter.isLoginSucesso("abc", "123"))
    }
}