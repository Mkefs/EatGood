package dev.cotapro.mx.recetas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ingrediente.class, Pasos.class, RecetasDAO.class})
public abstract class GuardadosDB extends RoomDatabase {
}
