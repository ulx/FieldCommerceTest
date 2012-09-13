package ru.mbsl.fcdirektiva.test;

import java.util.List;

import ru.mbsl.fcdirektiva.database.DataBaseManager;
import ru.mbsl.itemTable.ProductItem;
import ru.mbsl.models.FactoryModel;
import ru.mbsl.models.IModel;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class TestModelProduct extends AndroidTestCase {

	IModel model;
	ProductItem item;
	@Override
	protected void setUp() throws Exception {
		// Инициализируем менаджер с контекстом тестов
		DataBaseManager.getInstance(getContext());		
		model = FactoryModel.getProductsModel();	
		item = new ProductItem();
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
		ProductItem item = new ProductItem();
		item.name = "test";
		item.manufacturer = "test";
		item._id = model.save(item);
		ProductItem temp = (ProductItem)model.getItem(item._id);
		assertTrue(item.equals(temp));
	}
	
	/**
	 * Проверка обновления элемента в базе
	 */
	@MediumTest
	public void testUpdateItem(){
		ProductItem item = new ProductItem();
		item.name = "test";
		item.manufacturer = "test";
		item._id = model.save(item);
		item.name = "test2";
		item.manufacturer = "test2";
		model.save(item);
		ProductItem temp = (ProductItem)model.getItem(item._id);
		assertTrue(item.equals(temp));
	}
}
