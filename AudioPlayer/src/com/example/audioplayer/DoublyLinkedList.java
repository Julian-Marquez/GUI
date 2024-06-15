package com.example.audioplayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DoublyLinkedList implements Iterable<Song> {
    private Node head;
    private Node tail;
    private Node current;

    public void addSong(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
            current = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    public Song getNextSong() {
        if (current != null && current.getNext() != null) {
            current = current.getNext();
            return current.getSong();
        }
        return null;
    }

    public Song getPrevSong() {
        if (current != null && current.getPrev() != null) {
            current = current.getPrev();
            return current.getSong();
        }
        return null;
    }

    public Song getCurrentSong() {
        if (current != null) {
            return current.getSong();
        }
        return null;
    }

    public void setCurrentSong(Song song) {
    	Node songNode = new Node(song);
        this.current = songNode;
    }

    public void resetToHead() {
        current = head;
    }

    @Override
    public Iterator<Song> iterator() {
        return new Iterator<Song>() {
            private Node currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Song next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Song song = currentNode.getSong();
                currentNode = currentNode.getNext();
                return song;
            }
        };
    }

    // Method to retrieve all songs as a List
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        Node currentNode = head;
        while (currentNode != null) {
            songs.add(currentNode.getSong());
            currentNode = currentNode.getNext();
        }
        return songs;
    }

    private static class Node {
        private Song song;
        private Node next;
        private Node prev;

        public Node(Song song) {
            this.song = song;
        }

        public Song getSong() {
            return song;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
}
