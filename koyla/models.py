from django.db import models
from django.db.models.signals import post_save, post_delete

class GrammarCard(models.Model):
    "Is used as a separate entity as well as object through ForeignKey in Koyla and Card"
    title = models.CharField(max_length=100)
    body = models.TextField()
    comment = models.TextField()
    category = models.CharField(max_length=64)

    def __str__(self):
        return self.title + ": " + self.body  + ", " + self.comment + ", " + self.category

class Koyla(models.Model):
    "Word both in English and Mela with comments and optional GrammarCard"
    word = models.CharField(max_length=30)
    la = models.CharField(max_length=100)
    comment = models.CharField(max_length=1000)
    grammarCard = models.ForeignKey(GrammarCard, null=True, blank=True, on_delete=models.SET_NULL)

    def __str__(self):
        return self.word + " == " + self.la + ", comment: " + self.comment

class Card(models.Model):
    "Basic Word (Latay) with minimal info, rest of the info should be in Koyla and GrammarCard"
    front = models.CharField(max_length=30)
    back = models.CharField(max_length=100)
    flip = models.BooleanField()
    grammarCard = models.ForeignKey(GrammarCard, null=True, blank=True, on_delete=models.SET_NULL)

    def __str__(self):
        return self.front + " == " + self.back

class Intro(models.Model):
    "Introduction - home page"
    title = models.CharField(max_length=100)
    body = models.TextField()

class Alphabet(models.Model):
    "Alphabet that is used for grammar page"
    letter = models.CharField(max_length=2, default='a')
    name = models.CharField(max_length=10, default='a')
    example = models.CharField(max_length=100, default='car')

    def __str__(self):
        return self.letter + ", " + self.name + ", " + self.example

def create_koyla(sender, **kwargs):
    "Fn used to create Koyla when Card is created"

    instance = kwargs['instance']
    if kwargs['created'] and hasattr(instance, 'front') and hasattr(instance, 'back'):
        # Assuming instance is `Card`
        ki_koyla = Koyla.objects.create(word=instance.front, la=instance.back, comment="---")

post_save.connect(create_koyla, sender=Card)
