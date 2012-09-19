package ru.mbsl.fcdirektiva.test;

import java.util.List;

import ru.mbsl.fcdirektiva.database.DataBaseManager;
import ru.mbsl.fcdirektiva.itemTable.ClientItem;
import ru.mbsl.models.FactoryModel;
import ru.mbsl.models.IModel;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class TestModelClients extends AndroidTestCase  {	
	IModel model;
	
	@Override
	protected void setUp() throws Exception {
		// Инициализируем менаджер с контекстом тестов
		DataBaseManager.getInstance(getContext());		
		 model = FactoryModel.getClientsModel();	
		super.setUp();
	}
	
	/**
	 * Сохраннение элемента в таблицу
	 */
	@MediumTest
	public void testSaveInsert(){			
		model.clearDataBase();
		ClientItem item = new ClientItem();
		item.id_client = 1;
		long index = model.save(item);		
		assertTrue(index > 0);
	}

	/**
	 * Очистка всей таблицы
	 */
	@MediumTest
	public void testClearTable(){
		model.clearDataBase();
		ClientItem item = new ClientItem();
		item.id_client = 1;
		model.save(item);		
		model.clearDataBase();
		List<?> list = model.getItemsAll();
		assertTrue(list.size() == 0);
	}

	/**
	 * Удаление элемента
	 */
	@MediumTest
	public void testDeleteItem(){
		model.clearDataBase();
		ClientItem item = new ClientItem();		
		item.id_client = 1;
		model.save(item);
		int index = model.removeItem(item.id_client);
		assertEquals(1, index);
	}
	
	/**
	 * Получение списка элементов
	 */
	@MediumTest
	public void testGetItemsAll(){
		model.clearDataBase();
		ClientItem item = new ClientItem();
		item.id_client = 1;
		model.save(item);
		item.id_client = 2;
		model.save(item);
		item.id_client = 3;
		model.save(item);
		item.id_client = 4;
		model.save(item);
		List<?> list = model.getItemsAll();
		assertTrue(list.size() == 4);
	}
	
	/**
	 * Получение элемента по идентификатору
	 */
	@MediumTest
	public void testGetItem(){
		model.clearDataBase();
		ClientItem item = new ClientItem();
		item.id_client = 1;
		item.address = "test";
		item.group_id = 1;
		item.name = "test";
		item.status = 1;
		model.save(item);
		ClientItem temp = (ClientItem)model.getItemDB(item.id_client);
		assertTrue(item.equals(temp));
	}
	
	/**
	 * Проверка обновления элемента в базе
	 */
	@MediumTest
	public void testUpdateItem(){
		model.clearDataBase();
		ClientItem item = new ClientItem();
		item.id_client = 1;
		item.address = "test";
		item.group_id = 1;
		item.name = "test";
		item.status = 1;
		model.save(item);
		item.address = "test2";
		item.group_id = 12;
		item.name = "test2";
		item.status = 12;
		model.save(item);
		ClientItem temp = (ClientItem)model.getItemDB(item.id_client);
		assertTrue(item.equals(temp));
	}
	
}
