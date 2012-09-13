package ru.mbsl.fcdirektiva.test;

import java.util.List;

import ru.mbsl.fcdirektiva.database.DataBaseManager;
import ru.mbsl.itemTable.ClientItem;
import ru.mbsl.models.ClientsModel;
import ru.mbsl.models.FactoryModel;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class TestModelClients extends AndroidTestCase  {
	ClientsModel model = FactoryModel.getClientsModel();
	
	@Override
	protected void setUp() throws Exception {
		DataBaseManager.getInstance(getContext());
		super.setUp();
	}
	
	@MediumTest
	public void testSaveInsert(){			
		ClientItem item = new ClientItem();
		long index = model.save(item);		
		assertTrue(index > 0);
	}

	public void testClearTable(){
		ClientItem item = new ClientItem();
		model.save(item);		
		model.clearDataBase();
		List<ClientItem> list = model.getItemsAll();
		assertTrue(list.size() == 0);
	}

	
}
