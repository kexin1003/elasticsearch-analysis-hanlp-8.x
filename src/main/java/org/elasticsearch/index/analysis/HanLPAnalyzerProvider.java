package org.elasticsearch.index.analysis;

import com.hankcs.cfg.Configuration;
import com.hankcs.lucene.HanLPAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

/**
 * Project: elasticsearch-analysis-hanlp
 * Description: Hanlp analyzer provider
 * Author: Kenn
 * Create: 2018-12-14 15:10
 */
public class HanLPAnalyzerProvider extends AbstractIndexAnalyzerProvider<Analyzer> {

    private final Analyzer analyzer;

    public HanLPAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings, HanLPType hanLPType) {
        super( name, settings);
        Configuration configuration = new Configuration(env, settings);
        analyzer = new HanLPAnalyzer(configuration);
    }

    public static HanLPAnalyzerProvider getHanLPAnalyzerProvider(IndexSettings indexSettings, Environment env, String name,
                                                                 Settings settings) {
        return new HanLPAnalyzerProvider(indexSettings, env, name, settings, HanLPType.HANLP);
    }

    @Override
    public Analyzer get() {
        return this.analyzer;
    }
}
