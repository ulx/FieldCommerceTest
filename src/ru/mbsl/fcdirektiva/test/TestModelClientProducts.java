package ru.mbsl.fcdirektiva.test;

import java.util.List;

import ru.mbsl.fcdirektiva.database.DataBaseManager;
import ru.mbsl.fcdirektiva.itemTable.ClientProductItem;
import ru.mbsl.fcdirektiva.models.FactoryModel;
import ru.mbsl.fcdirektiva.models.IModel;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class TestModelClientProducts extends AndroidTestCase {

	IModel model;
	ClientProductItem item;
	@Override
	protected void setUp() throws Exception {
		// Инициализируем менаджер с контекстом тестов
		DataBaseManager.getInstance(getContext());		
		model = FactoryModel.getClientProductsModel();	
		item = new ClientProductItem();
		super.setUp();
	}
	
	/**
	 * Сохраннение элемента в таблицу
	 */
	@MediumTest
	public void testSaveInsert(){				
		long index = model.save(item);		
		assertTrue(index > 0);
	}

	/**
	 * Очистка всей таблицы
	 */
	@MediumTest
	public void testClearTable(){		
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
		ClientProductItem item = new ClientProductItem();
		item.id_client = 1;
		item.id_product = 1;
		item._id = model.save(item);
		ClientProductItem temp = (ClientProductItem)model.getItemDB(item._id);
		assertTrue(item.equals(temp));
	}
	
	/**
	 * Проверка обновления элемента в базе
	 */
	@MediumTest
	public void testUpdateItem(){
		ClientProductItem item = new ClientProductItem();
		item.id_client = 1;
		item.id_product = 1;
		item._id = model.save(item);
		item.id_client = 12;
		item.id_product = 12;
		model.save(item);
		ClientProductItem temp = (ClientProductItem)model.getItemDB(item._id);
		assertTrue(item.equals(temp));
	}
}
