from django.db import models

class GrammarCard(models.Model):
	title = models.CharField(max_length=100)
	body = models.TextField()
	comment = models.TextField()
	category = models.CharField(max_length=64)

	def __str__(self):
		return self.title + ": " + self.body  + ", " + self.comment + ", " + self.category

class Koyla(models.Model):
	word = models.CharField(max_length=30)
	la = models.CharField(max_length=30)
	comment = models.CharField(max_length=1000)
	grammarCard = models.ForeignKey(GrammarCard, null=True, blank=True, on_delete=models.SET_NULL)

	def __str__(self):
		return self.word + " == " + self.la + ", comment: " + self.comment

class Card(models.Model):
	front = models.CharField(max_length=100)
	back = models.CharField(max_length=100)
	flip = models.BooleanField()
	grammarCard = models.ForeignKey(GrammarCard, null=True, blank=True, on_delete=models.SET_NULL)

	def __str__(self):
		return self.front + " == " + self.back

class Intro(models.Model):
	title = models.CharField(max_length=100)
	body = models.TextField()

class Alphabet(models.Model):
	letter = models.CharField(max_length=2, default='a')
	name = models.CharField(max_length=10, default='a')
	example = models.CharField(max_length=100, default='car')

	def __str__(self):
		return self.letter + ", " + self.name + ", " + self.example