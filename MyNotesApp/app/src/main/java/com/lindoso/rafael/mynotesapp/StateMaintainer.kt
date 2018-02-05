package com.lindoso.rafael.mynotesapp

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.util.Log
import java.lang.ref.WeakReference


/**
 * Created by rlindoso on 17/11/2017.
 */
class StateMaintainer(fragmentManager: FragmentManager, private val mStateMaintenerTag: String)
/**
 * Construtor
 * @param fragmentManager       repassa uma referência do FragmentManager
 * @param stateMaintainerTAG      a TAG utilizada para inserir o fragmento responsável
 * por manter os objetos "vivos"
 */
 {
    protected val TAG = javaClass.simpleName
    private val mFragmentManager: WeakReference<FragmentManager> = WeakReference(fragmentManager)
    private lateinit var mStateMaintainerFrag: StateMngFragment

     /**
     * cria o fragmento responsável por armazenar o objetos
     * @return  true: criou o framentos e rodou pela primeira vez
     * false: o objeto já foi criado, portanto é apenas recuperado
     */
    fun firstTimeIn(): Boolean {
        try {
            // Recuperando referência
            mStateMaintainerFrag = (mFragmentManager.get()!!.findFragmentByTag(mStateMaintenerTag) as StateMngFragment?)!!

            // Criando novo RetainedFragment
            if (mStateMaintainerFrag == null) {
                Log.d(TAG, "Criando novo RetainedFragment " + mStateMaintenerTag)
                mStateMaintainerFrag = StateMngFragment()
                mFragmentManager.get()!!.beginTransaction().add(mStateMaintainerFrag, mStateMaintenerTag).commit()
                return true
            } else {
                Log.d(TAG, "Retornando retained fragment existente " + mStateMaintenerTag)
                return false
            }
        } catch (e: NullPointerException) {
            Log.w(TAG, "Erro firstTimeIn()")
            return false
        }

    }


    /**
     * Insere objeto a serem presenrvados durante mudanças de configuração
     * @param key   TAG de referência para recuperação do objeto
     * @param obj   Objeto a ser mantido
     */
    fun put(key: String, obj: Object) {
        mStateMaintainerFrag!!.put(key, obj)
    }

    /**
     * Insere objeto a serem presenrvados durante mudanças de configuração.
     * Utiliza a classe do Objeto como referência futura.
     * Só deve ser utilizado somente uma vez por classe, caso contrário haverá
     * possíveis conflitos na recuperação dos dados
     * @param obj   Objeto a ser mantido
     */
    fun put(obj: Object) {
        put(obj.javaClass.name, obj)
    }


    /**
     * Recupera o objeto salvo
     * @param key   Chave de referência do obj
     * @param <T>   tipo genérico de retorno
     * @return      Objeto armazenado
    </T> */
    operator fun <T> get(key: String): T? {
        return mStateMaintainerFrag!!.get(key)

    }

    /**
     * Verifica a existência de um objeto com a chave fornecida
     * @param key   Chave para verificação
     * @return      true: obj existe
     * false: obj insexistente
     */
    fun hasKey(key: String): Boolean {
        return (if (mStateMaintainerFrag != null) mStateMaintainerFrag.get(key) else null) != null
    }


    /**
     * Armazena e administra os objetos que devem ser preservados
     * durante mudanças de configuração.
     * É instanciado somente uma vez e utiliza um
     * `HashMap` para salvar os objetos e suas
     * chaves de referência.
     */
    class StateMngFragment : Fragment() {
        private val mData: HashMap<String, Object> = HashMap()

        override fun onCreate(savedInstanceState: Bundle) {
            super.onCreate(savedInstanceState)
            // Garante que o Fragmento será preservado
            // durante mudanças de configuração
            retainInstance = true
        }

        /**
         * Insere objetos no hashmap
         * @param key   Chave de referência
         * @param obj   Objeto a ser salvo
         */
        fun put(key: String, obj: Object) {
            mData.put(key, obj)
        }

        /**
         * Insere objeto utilizando o nome da classe como referência
         * @param object    Objeto a ser salvo
         */
        fun put(obj: Object) {
            put(obj.javaClass.name, obj)
        }

        /**
         * Recupera objeto salvo no hashmap
         * @param key   Chave de referência
         * @param <T>   Classe
         * @return      Objeto salvo
        </T> */
        fun <T> get(key: String): T {
            return mData[key] as T
        }
    }

}