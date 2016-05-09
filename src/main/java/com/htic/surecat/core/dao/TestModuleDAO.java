package com.htic.surecat.core.dao;

import com.htic.surecat.api.TestModule;
import com.htic.surecat.api.TestSource;

public interface TestModuleDAO {

	public TestModule populate (TestSource testSource) throws Exception;

}