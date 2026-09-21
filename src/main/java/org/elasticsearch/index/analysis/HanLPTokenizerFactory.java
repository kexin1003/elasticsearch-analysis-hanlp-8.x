package org.elasticsearch.index.analysis;

import com.hankcs.cfg.Configuration;
import com.hankcs.hanlp.HanLP;
import com.hankcs.lucene.TokenizerBuilder;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

/**
 * Project: elasticsearch-analysis-hanlp
 * Description: Hanlp tokenizer factory
 * Author: Kenn
 * Create: 2018-12-14 15:10
 */
public class HanLPTokenizerFactory extends AbstractTokenizerFactory {
    /**
     * 分词类型
     */
    private final HanLPType hanLPType;
    /**
     * 分词配置
     */
    private final Configuration configuration;

    public HanLPTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings, HanLPType hanLPType) {
        super(indexSettings, settings, name);
        this.hanLPType = hanLPType;
        this.configuration = new Configuration(env, settings);
    }

    public static HanLPTokenizerFactory getHanLPTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
                                                                 Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, env, name, settings, HanLPType.HANLP);
    }

    public static HanLPTokenizerFactory getHanLPStandardTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
                                                                         Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, env, name, settings, HanLPType.STANDARD);
    }

    public static HanLPTokenizerFactory getHanLPIndexTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
                                                                      Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, env, name, settings, HanLPType.INDEX);
    }

    public static HanLPTokenizerFactory getHanLPNLPTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
                                                                    Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, env, name, settings, HanLPType.NLP);
    }

    public static HanLPTokenizerFactory getHanLPCRFTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
                                                                    Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, env, name, settings, HanLPType.CRF);
    }

    public static HanLPTokenizerFactory getHanLPNShortTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
                                                                       Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, env, name, settings, HanLPType.N_SHORT);
    }

    public static HanLPTokenizerFactory getHanLPDijkstraTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
                                                                         Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, env, name, settings, HanLPType.DIJKSTRA);
    }

    public static HanLPTokenizerFactory getHanLPSpeedTokenizerFactory(IndexSettings indexSettings, Environment env, String name,
                                                                      Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, env, name, settings, HanLPType.SPEED);
    }

    /*@Override
    public Tokenizer create() {
        // The default HanLP segmenter uses HanLP's built-in dictionary.
        // Keep the ES plugin independent from Perceptron/CRF model loading.
        return TokenizerBuilder.tokenizer(
                AccessController.doPrivileged((PrivilegedAction<Segment>) HanLP::newSegment),
                configuration);
    }*/

    @Override
    public Tokenizer create() {
        return TokenizerBuilder.tokenizer(
                HanLP.newSegment(),
                configuration);
    }
}
