from django.contrib import admin

from .models import Koyla, Card, Intro, GrammarCard, Alphabet

class KoylaAdmin(admin.ModelAdmin):
	list_display = ('word', 'la', 'comment')
	search_fields = ('word', 'la')

admin.site.register(Koyla, KoylaAdmin)

class CardAdmin(admin.ModelAdmin):
	list_display = ('front', 'back')
	search_fields = ('front', 'back')

admin.site.register(Card, CardAdmin)
admin.site.register(Intro)
admin.site.register(GrammarCard)
admin.site.register(Alphabet)
