package ru.mbsl.fcdirektiva.test;

import java.util.List;

import ru.mbsl.fcdirektiva.database.DataBaseManager;
import ru.mbsl.itemTable.OrderHeaderItem;
import ru.mbsl.models.FactoryModel;
import ru.mbsl.models.IModel;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class TestModelOrderHeader extends AndroidTestCase {

	IModel model;
	OrderHeaderItem item;
	@Override
	protected void setUp() throws Exception {
		// Инициализируем менаджер с контекстом тестов
		DataBaseManager.getInstance(getContext());		
		model = FactoryModel.getOrderHeaderModel();	
		item = new OrderHeaderItem();
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
		OrderHeaderItem item = new OrderHeaderItem();
	item.finish_date = 100;
	item.id_client = 1;
	item.latitude = 123.123;
	item.longitude = 123.123;
	item.start_date = 100;
		item._id = model.save(item);
		OrderHeaderItem temp = (OrderHeaderItem)model.getItem(item._id);
		assertTrue(item.equals(temp));
	}
	
	/**
	 * Проверка обновления элемента в базе
	 */
	@MediumTest
	public void testUpdateItem(){
		OrderHeaderItem item = new OrderHeaderItem();
		item.finish_date = 100;
		item.id_client = 1;
		item.latitude = 123.123;
		item.longitude = 123.123;
		item.start_date = 100;
		item._id = model.save(item);
		item.finish_date = 200;
		item.id_client = 2;
		item.latitude = 1232.1232;
		item.longitude = 1232.1232;
		item.start_date = 200;
		model.save(item);
		OrderHeaderItem temp = (OrderHeaderItem)model.getItem(item._id);
		assertTrue(item.equals(temp));
	}
}
