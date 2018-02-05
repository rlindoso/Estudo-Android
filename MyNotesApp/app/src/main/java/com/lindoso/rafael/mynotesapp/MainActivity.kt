package com.lindoso.rafael.mynotesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.Toast
import com.lindoso.rafael.mynotesapp.notas.NotasMVP
import com.lindoso.rafael.mynotesapp.notas.NotasMVP.PresenterOps
import com.lindoso.rafael.mynotesapp.notas.NotasMVP.RequiredViewOps
import com.lindoso.rafael.mynotesapp.notas.NotasMVP.PresenterOps
import javax.swing.UIManager.put
import com.lindoso.rafael.mynotesapp.notas.NotasMVP.RequiredViewOps
import com.lindoso.rafael.mynotesapp.notas.NotasPresenter
import android.app.Activity
import android.support.v4.app.FragmentActivity.TAG
import android.util.Log.d
import android.widget.Toolbar
import com.lindoso.rafael.mynotesapp.notas.NotasMVP.PresenterOps




class MainActivity : AppCompatActivity(), NotasMVP.RequiredViewOps {
    protected val TAG = javaClass.simpleName

    // Responsável por manter estado dos objetos inscritos
    // durante mudanças de configuração
    private val mStateMaintainer = StateMaintainer(this.fragmentManager, TAG)

    // Operações no Presenter
    private val mPresenter: NotasMVP.PresenterOps? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMVPOps()
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val fab = findViewById(R.id.fab)
    }
    /**
     * Inicia e reinicia o Presenter. Este método precisa ser chamado
     * após [Activity.onCreate]
     */
    fun startMVPOps() {
        try {
            if (mStateMaintainer.firstTimeIn()) {
                d(FragmentActivity.TAG, "onCreate() chamado pela primera vez")
                initialize(this)
            } else {
                d(FragmentActivity.TAG, "onCreate() chamado mais de uma vez")
                reinitialize(this)
            }
        } catch (e: InstantiationException) {
            d(FragmentActivity.TAG, "onCreate() " + e)
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            d(FragmentActivity.TAG, "onCreate() " + e)
            throw RuntimeException(e)
        }

    }

    /**
     * Inicializa os objetos relevantes para o MVP.
     * Cria uma instância do Presenter, salva o presenter
     * no [StateMaintainer] e informa à instância do
     * presenter que objeto foi criado.
     * @param view      Operações no View exigidas pelo Presenter
     */
    @Throws(InstantiationException::class, IllegalAccessException::class)
    private fun initialize(view: MainNotas.RequiredViewOps) {
        mPresenter = NotasPresenter(view)
        mStateMaintainer.put(NotasMVP.PresenterOps::class.java!!.getSimpleName(), mPresenter)
    }


    /**
     * Recupera o presenter e informa à instância que houve uma mudança
     * de configuração no View.
     * Caso o presenter tenha sido perdido, uma nova instância é criada
     */
    @Throws(InstantiationException::class, IllegalAccessException::class)
    private fun reinitialize(view: NotasMVP.RequiredViewOps) {
        mPresenter = mStateMaintainer.get(NotasMVP.PresenterOps::class.java!!.getSimpleName())

        if (mPresenter == null) {
            Log.w(FragmentActivity.TAG, "recriando o Presenter")
            initialize(view)
        } else {
            mPresenter.onConfigurationChanged(view)
        }
    }

    override fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showAlert(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
