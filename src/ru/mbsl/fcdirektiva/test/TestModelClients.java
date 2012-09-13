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
	/**
	 * Выполняем операции перед прогоном тестов
	 */
	@Override
	protected void setUp() throws Exception {
		// Инициализируем менаджер с контекстом тестов
		DataBaseManager.getInstance(getContext());
		super.setUp();
	}
	
	/**
	 * Сохраннение элемента в таблицу
	 */
	@MediumTest
	public void testSaveInsert(){			
		ClientItem item = new ClientItem();
		long index = model.save(item);		
		assertTrue(index > 0);
	}

	/**
	 * Очистка всей таблицы
	 */
	@MediumTest
	public void testClearTable(){
		ClientItem item = new ClientItem();
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
		ClientItem item = new ClientItem();		
		item._id = model.save(item);
		int index = model.removeItem(item._id);
		assertEquals(1, index);
	}
	
	/**
	 * Получение списка элементов
	 */
	@MediumTest
	public void testGetItemsAll(){
		model.clearDataBase();
		ClientItem item = new ClientItem();
		model.save(item);
		model.save(item);
		model.save(item);
		model.save(item);
		List<?> list = model.getItemsAll();
		assertTrue(list.size() == 4);
	}
	
	/**
	 * Получение элемента по идентификатору
	 */
	@MediumTest
	public void testGetItem(){
		ClientItem item = new ClientItem();
		item.address = "test";
		item.group_id = 1;
		item.name = "test";
		item.status = 1;
		item._id = model.save(item);
		ClientItem temp = model.getItem(item._id);
		assertTrue(item.equals(temp));
	}
	
	/**
	 * Проверка обновления элемента в базе
	 */
	@MediumTest
	public void testUpdateItem(){
		ClientItem item = new ClientItem();
		item.address = "test";
		item.group_id = 1;
		item.name = "test";
		item.status = 1;
		item._id = model.save(item);
		item.address = "test2";
		item.group_id = 12;
		item.name = "test2";
		item.status = 12;
		model.save(item);
		ClientItem temp = model.getItem(item._id);
		assertTrue(item.equals(temp));
	}
	
}
