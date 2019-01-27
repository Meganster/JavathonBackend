package com.javathon.backend.model.db.events.impl;

import com.javathon.backend.model.db.events.interf.Event;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
public class EventMessage extends Event {
}
