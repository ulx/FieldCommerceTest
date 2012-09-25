package ru.mbsl.fcdirektiva.test;

import java.util.List;

import ru.mbsl.fcdirektiva.database.DataBaseManager;
import ru.mbsl.fcdirektiva.itemTable.ProductItem;
import ru.mbsl.fcdirektiva.models.FactoryModel;
import ru.mbsl.fcdirektiva.models.IModel;
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
		model.clearDataBase();
		item.id_product = 1;
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
		model.clearDataBase();
		item.id_product = 1;
		model.save(item);
		int index = model.removeItem(item.id_product);
		assertEquals(1, index);
	}
	
	/**
	 * Получение списка элементов
	 */
	@MediumTest
	public void testGetItemsAll(){
		model.clearDataBase();
		item.id_product = 1;
		model.save(item);
		item.id_product = 2;
		model.save(item);
		item.id_product = 3;
		model.save(item);
		item.id_product = 4;
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
		ProductItem item = new ProductItem();
		item.id_product = 1;
		item.name = "test";
		item.manufacturer = "test";
		model.save(item);
		ProductItem temp = (ProductItem)model.getItemDB(item.id_product);
		assertTrue(item.equals(temp));
	}
	
	/**
	 * Проверка обновления элемента в базе
	 */
	@MediumTest
	public void testUpdateItem(){
		model.clearDataBase();
		ProductItem item = new ProductItem();
		item.id_product = 1;
		item.name = "test";
		item.manufacturer = "test";
		model.save(item);
		item.name = "test2";
		item.manufacturer = "test2";
		model.save(item);
		ProductItem temp = (ProductItem)model.getItemDB(item.id_product);
		assertTrue(item.equals(temp));
	}
}
